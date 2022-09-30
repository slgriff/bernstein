package bernstein.repository.impl;

import bernstein.domain.Artifact;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import bernstein.repository.ApplicationRepository;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class ApplicationRepositoryTestBase {
    private final Artifact artifact1 = Artifact.builder().name("TEST_ARTIFACT_NAME1").build();
    private final Artifact artifact2 = Artifact.builder().name("TEST_ARTIFACT_NAME2").build();
    private final Artifact artifact3 = Artifact.builder().name("TEST_ARTIFACT_NAME3").build();

    private final VersionedArtifact versionedArtifact1v1 = VersionedArtifact.builder().name(artifact1.getName())
            .version("TEST_VERSION_1").createdAt(Timestamp.from(Instant.now())).build();
    private final VersionedArtifact versionedArtifact1v2 = VersionedArtifact.builder().name(artifact1.getName())
            .version("TEST_VERSION_2").createdAt(Timestamp.from(Instant.now())).build();
    private final VersionedArtifact versionedArtifact2v1 = VersionedArtifact.builder().name(artifact2.getName())
            .version("TEST_VERSION_1").createdAt(Timestamp.from(Instant.now())).build();
    private final VersionedArtifact versionedArtifact3v1 = VersionedArtifact.builder().name(artifact3.getName())
            .version("TEST_VERSION_1").createdAt(Timestamp.from(Instant.now())).build();

    private final Environment environment1 = Environment.builder().name("TEST_ENVIRONMENT1_NAME").build();
    private final Environment environment2 = Environment.builder().name("TEST_ENVIRONMENT2_NAME").build();
    private final Environment environment3 = Environment.builder().name("TEST_ENVIRONMENT3_NAME").build();
    private final Environment environment4 = Environment.builder().name("TEST_ENVIRONMENT4_NAME").build();

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void shouldWork1() {
        val artifacts = applicationRepository.getArtifacts();
        assertThat(artifacts).isEmpty();
    }

    @Test
    public void shouldWork2() {
        applicationRepository.insertArtifact(artifact1);

        val artifacts = applicationRepository.getArtifacts();

        assertThat(artifacts).isEqualTo(List.of(artifact1));
    }

    @Test
    public void shouldWork3() {
        applicationRepository.insertArtifact(artifact1);
        applicationRepository.insertArtifact(artifact2);
        applicationRepository.insertArtifact(artifact3);

        val artifacts = applicationRepository.getArtifacts();
        assertThat(artifacts).isEqualTo(List.of(artifact1, artifact2, artifact3));
    }

    @Test
    public void shouldWork4() {
        val deployments = applicationRepository.getDeploymentsByEnvironmentAndArtifact(environment1, artifact1);
        assertThat(deployments).isEmpty();
    }

    @Test
    public void shouldWork5() {
        applicationRepository.insertEnvironment(environment1);
        applicationRepository.insertArtifact(artifact1);
        applicationRepository.insertVersionedArtifact(versionedArtifact1v1);
        applicationRepository.insertDeployment(environment1, versionedArtifact1v1);

        val deployments = applicationRepository.getDeploymentsByEnvironmentAndArtifact(environment1, artifact1);
        assertThat(deployments).hasSize(1);
    }

    @Test
    public void shouldWork6() {
        applicationRepository.insertEnvironment(environment1);
        applicationRepository.insertEnvironment(environment2);
        applicationRepository.insertEnvironment(environment3);
        applicationRepository.insertEnvironment(environment4);

        applicationRepository.insertArtifact(artifact1);
        applicationRepository.insertArtifact(artifact2);
        applicationRepository.insertArtifact(artifact3);

        applicationRepository.insertVersionedArtifact(versionedArtifact1v1);
        applicationRepository.insertVersionedArtifact(versionedArtifact1v2);
        applicationRepository.insertVersionedArtifact(versionedArtifact2v1);
        applicationRepository.insertVersionedArtifact(versionedArtifact3v1);

        applicationRepository.insertDeployment(environment1, versionedArtifact1v1);
        applicationRepository.insertDeployment(environment1, versionedArtifact1v1); // test retry deploy same version
        applicationRepository.insertDeployment(environment1, versionedArtifact1v2);
        applicationRepository.insertDeployment(environment2, versionedArtifact2v1);
        applicationRepository.insertDeployment(environment3, versionedArtifact3v1);

        val deployments = applicationRepository.getDeploymentsByEnvironmentAndArtifact(environment1, artifact1);
        assertThat(deployments).hasSize(3);
    }

    @Test
    public void shouldWork7() {
        val environments = applicationRepository.getEnvironments();
        assertThat(environments).isEmpty();
    }

    @Test
    public void shouldWork8() {
        applicationRepository.insertEnvironment(environment1);

        val environments = applicationRepository.getEnvironments();
        assertThat(environments).isEqualTo(List.of(environment1));
    }

    @Test
    public void shouldWork9() {
        applicationRepository.insertEnvironment(environment1);
        applicationRepository.insertEnvironment(environment2);
        applicationRepository.insertEnvironment(environment3);
        applicationRepository.insertEnvironment(environment4);

        val environments = applicationRepository.getEnvironments();
        assertThat(environments).isEqualTo(List.of(environment1, environment2, environment3, environment4));
    }
}
