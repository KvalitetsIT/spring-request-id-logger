package dk.kvalitetsit.logging.configuration;

import dk.kvalitetsit.logging.LoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestIdLoggingWebMvcConfiguration implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(RequestIdLoggingWebMvcConfiguration.class);

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.debug("Adding request id logging interceptor.");
        registry.addInterceptor(loggingInterceptor);
    }
}
