# reactive-couchbase-service
Project to read data from couchbase using reactive programming.

## Api's

## /allstream
Streaming list of employees read from couchbase database / stub if couchbase is not configured, client calling this api if configured with Eventsource data will be read as event stream.

## /all
REST api call to get list of employees without streaming with bau request/response method from couchbase / stub if couchbase is not configured.

## employee/{id}
REST api call to get specific employee

## /init
To load data inital data to couchbase

## /addEmployee
Add specific record of employee to DB / stub if couchbase is not configured.

