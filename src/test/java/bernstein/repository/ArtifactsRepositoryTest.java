package bernstein.repository;

import bernstein.domain.Artifact;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class ArtifactsRepositoryTest {
    private final Artifact artifact1 = Artifact.builder().name("TEST_ARTIFACT_NAME1").build();
    private final Artifact artifact2 = Artifact.builder().name("TEST_ARTIFACT_NAME2").build();
    private final Artifact artifact3 = Artifact.builder().name("TEST_ARTIFACT_NAME3").build();

    @Autowired
    private ArtifactsRepository artifactsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldWork1() {
        val artifacts = artifactsRepository.getArtifacts();
        assertThat(artifacts).isEmpty();
    }

    @Test
    public void shouldWork2() {
        jdbcTemplate.update("INSERT INTO artifacts(name) VALUES(?)", artifact1.getName());
        val artifacts = artifactsRepository.getArtifacts();

        assertThat(artifacts).hasSize(1);
    }

    @Test
    public void shouldWork3() {
        jdbcTemplate.update("INSERT INTO artifacts(name) VALUES(?)", artifact1.getName());
        jdbcTemplate.update("INSERT INTO artifacts(name) VALUES(?)", artifact2.getName());
        jdbcTemplate.update("INSERT INTO artifacts(name) VALUES(?)", artifact3.getName());

        val artifacts = artifactsRepository.getArtifacts();
        assertThat(artifacts).hasSize(3);
    }
}
