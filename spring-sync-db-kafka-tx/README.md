# spring-sync-db-kafka-tx

How to synchronize/bind db & kafka transactions?

## Run config

Set the following variables in IntelliJ run config:

```
DATABASE_USERNAME=
DATABASE_PASSWORD=
DATABASE_URL=jdbc:postgresql://localhost:5432/${DB_NAME}
KAFKA_SERVER=
KAFKA_USERNAME=
KAFKA_PASSWORD=
```

## Research

In all the following cases, we want to have either all entities committed and sent to kafka or nothing:

1. POST /users
2. POST /users?dbfail=true
3. POST /users/bulk?when=1 (non-zero value)

Case #3 is failing in the current version of `UserService#createUsers`, implemented as `users.forEach { save; send; }`.
What happens here is that if the second `save` fails, the whole db tx is rolled back, leaving no knew items in the db, but the first `send` is still executed, sending the first item to kafka and creating an inconsistency between the db and kafka.
