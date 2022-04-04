package dk.kvalitetsit.logging.configuration;

import dk.kvalitetsit.logging.LoggingInterceptor;
import dk.kvalitetsit.logging.RequestIdGenerator;
import dk.kvalitetsit.logging.RequestIdGeneratorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class RequestIdLoggingConfiguration {
    @Bean
    public LoggingInterceptor loggingInterceptor(RequestIdGenerator requestIdGenerator) {
        return new LoggingInterceptor(requestIdGenerator);
    }

    @Bean
    @RequestScope
    public RequestIdGenerator requestIdGenerator(HttpServletRequest request, @Value("${correlation_id:X-REQUEST-ID}") String correlationIdHeaderName) {
        return new RequestIdGeneratorImpl(correlationIdHeaderName, request);
    }
}
