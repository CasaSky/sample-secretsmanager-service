#!/usr/bin/env bash

set -e

docker-compose up -d
awslocal secretsmanager create-secret --name local/casasky/db --secret-string file://local_db_secrets.json
awslocal secretsmanager create-secret --name local/casasky/encryption --secret-string file://local_encryption_key_secrets.json