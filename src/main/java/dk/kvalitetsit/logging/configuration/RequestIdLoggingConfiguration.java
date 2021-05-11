package dk.kvalitetsit.logging.configuration;

import dk.kvalitetsit.logging.LoggingInterceptor;
import dk.kvalitetsit.logging.RequestIdGenerator;
import dk.kvalitetsit.logging.RequestIdGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class RequestIdLoggingConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Bean
    public LoggingInterceptor loggingInterceptor(@Value("${correlation_id:correlation-id}") String correlationIdHeaderName, RequestIdGenerator requestIdGenerator) {
        return new LoggingInterceptor(correlationIdHeaderName, requestIdGenerator);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }

    @Bean
    @RequestScope
    public RequestIdGenerator requestIdGenerator(HttpServletRequest request, @Value("${correlation_id:correlation-id}") String correlationIdHeaderName) {
        return new RequestIdGeneratorImpl(correlationIdHeaderName, request);
    }
}
