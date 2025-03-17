#!/bin/bash
mkdir -p /vault/data
chown -R 100:100 /vault/data
exec vault server -config=/vault/config/config.hcl