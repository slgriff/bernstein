package bernstein.repository;

import bernstein.domain.Environment;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class EnvironmentsRepositoryTest {
    private final Environment environment1 = Environment.builder().name("TEST_ENVIRONMENT1_NAME").build();
    private final Environment environment2 = Environment.builder().name("TEST_ENVIRONMENT2_NAME").build();
    private final Environment environment3 = Environment.builder().name("TEST_ENVIRONMENT3_NAME").build();
    private final Environment environment4 = Environment.builder().name("TEST_ENVIRONMENT4_NAME").build();

    @Autowired
    private EnvironmentsRepository environmentsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldWork1() {
        val environments = environmentsRepository.getEnvironments();
        assertThat(environments).isEmpty();
    }

    @Test
    public void shouldWork2() {
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment1.getName());
        val environments = environmentsRepository.getEnvironments();
        assertThat(environments).hasSize(1);
    }

    @Test
    public void shouldWork3() {
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment1.getName());
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment2.getName());
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment3.getName());
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment4.getName());
        val environments = environmentsRepository.getEnvironments();
        assertThat(environments).hasSize(4);
    }
}
