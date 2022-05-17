package bernstein.service;

import bernstein.repository.DeploymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest
public class DeploymentsServiceTest {

    @MockBean
    private DeploymentsRepository deploymentsRepository;

    @MockBean
    private JmsTemplate jmsTemplate;

    @Autowired
    private DeploymentsService deploymentsService;


}
