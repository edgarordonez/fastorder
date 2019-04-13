#!/bin/sh

cd "$(dirname "$0")/../"

rm -rf .git/hooks

ln -s ../hooks/ .git/hooks
sudo chmod -R 777 hooks/*
