import dk.kvalitetsit.logging.RequestIdGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RequestIdGeneratorImplTest {
    private final String HEADER_NAME = "header_name";
    private RequestIdGeneratorImpl requestIdGenerator;
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    public void setup() {
        httpServletRequest = Mockito.mock(HttpServletRequest.class);
        requestIdGenerator = new RequestIdGeneratorImpl(HEADER_NAME, httpServletRequest);
    }

    @Test
    public void generateRequestIdFoundInHeader() {
        var input = "header_value";

        Mockito.when(httpServletRequest.getHeader(HEADER_NAME)).thenReturn(input);

        var result = requestIdGenerator.getOrGenerateRequestId();
        assertNotNull(result);
        assertEquals(input, result);
    }

    @Test
    public void generateRequestIdNotFoundInHeader() {
        var result = requestIdGenerator.getOrGenerateRequestId();
        assertNotNull(result);
        UUID.fromString(result);
    }

    @Test
    public void generateSameRequestId() {
        var result = requestIdGenerator.getOrGenerateRequestId();
        assertNotNull(result);

        var secondResult = requestIdGenerator.getOrGenerateRequestId();
        assertEquals(result, secondResult);
    }
}
