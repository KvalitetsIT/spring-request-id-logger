[![Build And Test](https://github.com/KvalitetsIT/spring-request-id-logger/actions/workflows/build.yml/badge.svg)](https://github.com/KvalitetsIT/spring-request-id-logger/actions/workflows/build.yml) ![Test Coverage](.github/badges/jacoco.svg)
# spring-request-id-logger
Simple library to add Spring interceptor that ensures that a correlation ID is put in the MDC logger context.
The correlation id is taken from an HTTP header.

## Using
To use the library simply include it in your pom.xml file. Then Spring magic takes care of the rest.  

To get the id logged to your log files you need to include something like below in your logging configuration. Below is for logback. 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
            <providers>
                <timestamp />
                <pattern>
                    <pattern>
                        {
                        "logger": "%logger",
                        "level": "%level",
                        "correlation-id": "%X{correlation-id}",
                        "thread": "%thread",
                        "message": "%m"
                        }
                    </pattern>
                </pattern>
                <stackTrace/>
            </providers>
        </encoder>
    </appender>

    <logger name="dk.medcom.sms" level="${LOG_LEVEL}" additivity="false">
        <appender-ref ref="STDOUT" />
    </logger>

    <root level="${LOG_LEVEL_FRAMEWORK}">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```
If you need access to the correlation id you can inject the interface dk.kvalitetsit.logging.RequestIdGenerator.

## Configuration
|Property | Description |   
|---|---|
|correlation_id |The HTTP header to use as request-id/correlation-id. If not specified it defaults to X-REQUEST-ID. |
----------

test


