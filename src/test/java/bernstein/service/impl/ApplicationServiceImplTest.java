package bernstein.service.impl;

import bernstein.domain.Environment;
import bernstein.repository.ApplicationRepository;
import bernstein.service.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class ApplicationServiceImplTest {
    private final Environment environment1 = Environment.builder().name("TEST_ENVIRONMENT1_NAME").build();
    private final Environment environment2 = Environment.builder().name("TEST_ENVIRONMENT2_NAME").build();
    private final Environment environment3 = Environment.builder().name("TEST_ENVIRONMENT3_NAME").build();
    private final Environment environment4 = Environment.builder().name("TEST_ENVIRONMENT4_NAME").build();

    @MockBean
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @Test
    public void shouldWork1() {
        List<Environment> environments = applicationService.getEnvironments();
        assertThat(environments).isEmpty();
    }

    @Test
    public void shouldWork2() {
        List<Environment> expectedResult = List.of(environment1);
        when(applicationRepository.getEnvironments()).thenReturn(expectedResult);

        List<Environment> environments = applicationService.getEnvironments();

        assertThat(environments).hasSize(1).containsAll(expectedResult);
    }

    @Test
    public void shouldWork3() {
        List<Environment> expectedResult = List.of(environment1, environment2, environment3, environment4);
        when(applicationRepository.getEnvironments()).thenReturn(expectedResult);

        List<Environment> environments = applicationService.getEnvironments();

        assertThat(environments).hasSize(4).containsAll(expectedResult);
    }
}
