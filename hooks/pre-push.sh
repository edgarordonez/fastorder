#!/bin/bash

# Checks if locally staged changes are formatted properly ignoring non-staged changes.
# Intended as git pre-push hook

PATH=$PATH:/usr/local/bin:/usr/local/sbin

echo ""
echo "Running pre-push hook… (you can omit this with --no-verify, but don't)"

echo "* Moving to the project directory…"
_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
DIR=$( echo $_DIR | sed 's/\/.git\/hooks$//' )

echo "* Stashing non-staged changes so we avoid checking them…"
git diff --quiet
hadNoNonStagedChanges=$?

if ! [ $hadNoNonStagedChanges -eq 0 ]
then
	git stash --keep-index -u > /dev/null
fi

echo "* Checking pre push conditions ('prep' SBT task)."
sbt prep > /dev/null
canPush=$?

if [ $canPush -ne 0 ]
then
	echo "* No!"
fi

echo "* Applying the stash with the non-staged changes."
if ! [ $hadNoNonStagedChanges -eq 0 ]
then
	sleep 1 && git stash pop --index > /dev/null & # sleep because otherwise commit fails when this leads to a merge conflict
fi

if [ $canPush -eq 0 ]
then
    echo "... done. Proceeding with commit."
    exit 0
else
    echo "CANCELLING commit due to test code style error (run 'sbt prep' for more information)"
    exit 1
fi
