package bernstein.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class VersionedArtifactsRepositoryTest {


    @Autowired
    private DeploymentsRepository deploymentsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testA() {}
}
