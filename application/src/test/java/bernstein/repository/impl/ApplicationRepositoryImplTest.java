package bernstein.repository.impl;

import bernstein.repository.ApplicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class ApplicationRepositoryImplTest {

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void shouldWork() {

    }
}
