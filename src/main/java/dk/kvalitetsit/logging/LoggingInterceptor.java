package dk.kvalitetsit.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    public static final String MDC_CORRELATION_ID = "correlation-id";

    private final String correlationIdHeaderName;
    private RequestIdGenerator requestIdGenerator;

    public LoggingInterceptor(String correlationIdHeaderName, RequestIdGenerator requestIdGenerator) {
        this.correlationIdHeaderName = correlationIdHeaderName;
        this.requestIdGenerator = requestIdGenerator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String correlationId = requestIdGenerator.getOrGenerateRequestId();
        MDC.put(MDC_CORRELATION_ID, correlationId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        MDC.clear();
    }
}
