package bernstein;

import bernstein.service.ApplicationService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ApplicationTest {

    @MockBean
    private ApplicationService applicationService;

    @Test
    public void contextLoads() {
    }
}
