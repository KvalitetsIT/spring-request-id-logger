package dk.kvalitetsit.logging;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public class RequestIdGeneratorImpl implements RequestIdGenerator {
    private final static Logger logger = LoggerFactory.getLogger(RequestIdGeneratorImpl.class);
    private final String headerName;
    private final HttpServletRequest httpServletRequest;

    private String cachedCorrelationId;

    public RequestIdGeneratorImpl(String headerName, HttpServletRequest httpServletRequest) {
        this.headerName = headerName;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String getOrGenerateRequestId() {
        if(cachedCorrelationId == null) {
            String correlationId = httpServletRequest.getHeader(headerName);
            logger.debug("Extracted header: {} with value:{}", headerName, correlationId);
            if(correlationId == null) {
                correlationId = UUID.randomUUID().toString();
                logger.debug("Generated new correlation id as it was not found in header: {}.", correlationId);
            }

            cachedCorrelationId = correlationId;
        }

        return cachedCorrelationId;
    }
}
