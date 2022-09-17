package bernstein.repository;

import bernstein.domain.Artifact;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class DeploymentsRepositoryTest {
    private final Artifact artifact1 = Artifact.builder().name("TEST_ARTIFACT_NAME1").build();
    private final Artifact artifact2 = Artifact.builder().name("TEST_ARTIFACT_NAME2").build();
    private final Artifact artifact3 = Artifact.builder().name("TEST_ARTIFACT_NAME3").build();

    private final VersionedArtifact versionedArtifact1v1 = VersionedArtifact.builder().name(artifact1.getName()).version("TEST_VERSION_1").build();
    private final VersionedArtifact versionedArtifact1v2 = VersionedArtifact.builder().name(artifact1.getName()).version("TEST_VERSION_2").build();
    private final VersionedArtifact versionedArtifact2v1 = VersionedArtifact.builder().name(artifact2.getName()).version("TEST_VERSION_1").build();
    private final VersionedArtifact versionedArtifact3v1 = VersionedArtifact.builder().name(artifact3.getName()).version("TEST_VERSION_1").build();

    private final Environment environment1 = Environment.builder().name("TEST_ENVIRONMENT1_NAME").build();
    private final Environment environment2 = Environment.builder().name("TEST_ENVIRONMENT2_NAME").build();
    private final Environment environment3 = Environment.builder().name("TEST_ENVIRONMENT3_NAME").build();
    private final Environment environment4 = Environment.builder().name("TEST_ENVIRONMENT4_NAME").build();

    @Autowired
    private DeploymentsRepository deploymentsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldWork1() {
        val deployments = deploymentsRepository.getDeploymentsByEnvironmentAndArtifact(environment1, artifact1);
        assertThat(deployments).isEmpty();
    }

    @Test
    public void shouldWork2() {
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment1.getName());
        jdbcTemplate.update("INSERT INTO artifacts(name) VALUES(?)", artifact1.getName());
        jdbcTemplate.update("INSERT INTO versioned_artifacts(artifact_name, artifact_version) VALUES(?, ?)",
                versionedArtifact1v1.getName(), versionedArtifact1v1.getVersion());
        jdbcTemplate.update("INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)",
                environment1.getName(), versionedArtifact1v1.getName(), versionedArtifact1v1.getVersion());

        val deployments = deploymentsRepository.getDeploymentsByEnvironmentAndArtifact(environment1, artifact1);
        assertThat(deployments).hasSize(1);
    }

    @Test
    public void shouldWork3() {
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment1.getName());
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment2.getName());
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment3.getName());
        jdbcTemplate.update("INSERT INTO environments(name) VALUES(?)", environment4.getName());

        jdbcTemplate.update("INSERT INTO artifacts(name) VALUES(?)", artifact1.getName());
        jdbcTemplate.update("INSERT INTO artifacts(name) VALUES(?)", artifact2.getName());
        jdbcTemplate.update("INSERT INTO artifacts(name) VALUES(?)", artifact3.getName());

        jdbcTemplate.update("INSERT INTO versioned_artifacts(artifact_name, artifact_version) VALUES(?, ?)",
                versionedArtifact1v1.getName(), versionedArtifact1v1.getVersion());
        jdbcTemplate.update("INSERT INTO versioned_artifacts(artifact_name, artifact_version) VALUES(?, ?)",
                versionedArtifact1v2.getName(), versionedArtifact1v2.getVersion());
        jdbcTemplate.update("INSERT INTO versioned_artifacts(artifact_name, artifact_version) VALUES(?, ?)",
                versionedArtifact2v1.getName(), versionedArtifact2v1.getVersion());
        jdbcTemplate.update("INSERT INTO versioned_artifacts(artifact_name, artifact_version) VALUES(?, ?)",
                versionedArtifact3v1.getName(), versionedArtifact3v1.getVersion());

        jdbcTemplate.update("INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)",
                environment1.getName(), versionedArtifact1v1.getName(), versionedArtifact1v1.getVersion());
        jdbcTemplate.update("INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)",
                environment1.getName(), versionedArtifact1v1.getName(), versionedArtifact1v1.getVersion());
        jdbcTemplate.update("INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)",
                environment1.getName(), versionedArtifact1v2.getName(), versionedArtifact1v2.getVersion());
        jdbcTemplate.update("INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)",
                environment1.getName(), versionedArtifact2v1.getName(), versionedArtifact2v1.getVersion());
        jdbcTemplate.update("INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)",
                environment1.getName(), versionedArtifact3v1.getName(), versionedArtifact3v1.getVersion());

        val deployments = deploymentsRepository.getDeploymentsByEnvironmentAndArtifact(environment1, artifact1);
        assertThat(deployments).hasSize(3);
    }
}
