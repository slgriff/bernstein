package bernstein.repository;

import bernstein.domain.Artifact;
import bernstein.domain.Environment;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@Transactional
public class DeploymentsRepositoryTest {

    private static final String TEST_ARTIFACT_NAME1 = "test-artifact-for-deployment";

    private static final String TEST_ARTIFACT_VERSION1 = "";
    private static final String TEST_ARTIFACT_VERSION2 = "";
    private static final String TEST_ARTIFACT_VERSION3 = "";

    private static final String TEST_ENVIRONMENT1_NAME = "A";
    private static final String TEST_ENVIRONMENT2_NAME = "B";
    private static final String TEST_ENVIRONMENT3_NAME = "C";
    private static final String TEST_ENVIRONMENT4_NAME = "D";

    private final Artifact artifactv1 = Artifact.builder().name(TEST_ARTIFACT_NAME1).version(TEST_ARTIFACT_VERSION1).build();
    private final Artifact artifactv2 = Artifact.builder().name(TEST_ARTIFACT_NAME1).version(TEST_ARTIFACT_VERSION2).build();
    private final Artifact artifactv3 = Artifact.builder().name(TEST_ARTIFACT_NAME1).version(TEST_ARTIFACT_VERSION3).build();

    private final Environment environment1 = Environment.builder().name(TEST_ENVIRONMENT1_NAME).build();
    private final Environment environment2 = Environment.builder().name(TEST_ENVIRONMENT2_NAME).build();
    private final Environment environment3 = Environment.builder().name(TEST_ENVIRONMENT3_NAME).build();
    private final Environment environment4 = Environment.builder().name(TEST_ENVIRONMENT4_NAME).build();

    @Autowired
    private DeploymentsRepository deploymentsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testA() {
        assertThatExceptionOfType(EmptyResultDataAccessException.class)
                .isThrownBy(() -> deploymentsRepository.getDeploymentById(1));
    }

    @Test
    @Disabled
    public void testB() {
        fail("fffff");
    }


}
