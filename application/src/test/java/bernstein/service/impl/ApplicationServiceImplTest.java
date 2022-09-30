package bernstein.service.impl;

import bernstein.repository.ApplicationRepository;
import bernstein.service.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class ApplicationServiceImplTest {

    @MockBean
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @Test
    public void shouldWork() {

    }

}
