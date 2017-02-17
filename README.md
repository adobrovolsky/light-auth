# Light Auth
## Before launching
```
psql -U postgres
create database lightauth;
\c lightauth;
insert into account (id, first_name, last_name, login, password, token, enabled) values 
(1, 'ivan', 'ivanov', 'vano', '123', 'token_vano', true),
(2, 'Stepan', 'Stepanov', 'stepa', '123', 'token_stepa', true);

```
## How to use it
```
# Get user
curl --header "X-TOKEN: token" http://localhost:8080/lightauth/user/1

# Authenticate user
curl -X POST -d '{"login":"vano", "pass": "123"}' -H "Accept: application/json" -H "Content-type: application/json"  http://localhost:8080/lightauth/auth

```
