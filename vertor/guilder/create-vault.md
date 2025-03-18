# vault operator init

int vault: vault operator init

unseal vault: vault operator unseal xxxxxxxxxxxxxxxxxxxx

login vault: vault login hvs.xxxxxxxxxxxx

create method secrets : vault secrets enable -path=authentication-client kv

put key in path : vault kv put authentication-client/private_key 
