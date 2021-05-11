# spring-request-id-logger
Simple library to add Spring interceptor that ensures that a correlation ID is put in the MDC context.
The correlation id is taken from an HTTP header.

## Using
To use the library simply include it in your pom.xml file. Then Spring magic takes care of the rest.  

If you need access to the correlation id you can inject the interface dk.kvalitetsit.logging.RequestIdGenerator.

## Configuration
|Property | Description |   
|---|---|
|correlation_id |The HTTP header to use as request-id/correlation-id. If not specified it defaults to X-REQUEST-ID. |
----------



