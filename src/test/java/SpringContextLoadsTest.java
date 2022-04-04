import dk.kvalitetsit.logging.configuration.RequestIdLoggingConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RequestIdLoggingConfiguration.class)
public class SpringContextLoadsTest {
    @Test
    public void contextLoads() {
    }
}
