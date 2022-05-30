package bernstein.service;

import bernstein.repository.DeploymentsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class DeploymentsServiceTest {

    @MockBean
    private DeploymentsRepository deploymentsRepository;

    @Autowired
    private DeploymentsService deploymentsService;

    @Test
    public void test() {
        deploymentsService.createDeploymentJob();
    }

}
