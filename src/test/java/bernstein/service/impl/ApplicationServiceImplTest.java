package bernstein.service.impl;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import bernstein.repository.ApplicationRepository;
import bernstein.service.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;
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

    private final Deployment deployment1 = Deployment.builder().id(1).environment(environment1)
            .versionedArtifact(versionedArtifact1v1).createdAt(Timestamp.from(Instant.now()))
            .status(Deployment.Status.IN_PROGRESS).build();

    private final Deployment deployment2 = Deployment.builder().id(2).environment(environment2)
            .versionedArtifact(versionedArtifact1v1).createdAt(Timestamp.from(Instant.now()))
            .finishedAt(Timestamp.from(Instant.now())).status(Deployment.Status.CANCELLED).build();

    private final Deployment deployment3 = Deployment.builder().id(3).environment(environment3)
            .versionedArtifact(versionedArtifact1v1).createdAt(Timestamp.from(Instant.now()))
            .finishedAt(Timestamp.from(Instant.now())).status(Deployment.Status.SUCCESS).build();

    private final Deployment deployment4 = Deployment.builder().id(4).environment(environment1)
            .versionedArtifact(versionedArtifact1v1).createdAt(Timestamp.from(Instant.now()))
            .finishedAt(Timestamp.from(Instant.now())).status(Deployment.Status.FAILURE).build();

    private final Deployment deployment5 = Deployment.builder().id(5).environment(environment1)
            .versionedArtifact(versionedArtifact1v2).createdAt(Timestamp.from(Instant.now()))
            .status(Deployment.Status.IN_PROGRESS).build();

    private final Deployment deployment6 = Deployment.builder().id(6).environment(environment2)
            .versionedArtifact(versionedArtifact2v1).createdAt(Timestamp.from(Instant.now()))
            .finishedAt(Timestamp.from(Instant.now())).status(Deployment.Status.SUCCESS).build();

    private final Deployment deployment7 = Deployment.builder().id(7).environment(environment4)
            .versionedArtifact(versionedArtifact3v1).createdAt(Timestamp.from(Instant.now()))
            .finishedAt(Timestamp.from(Instant.now())).status(Deployment.Status.FAILURE).build();

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

        assertThat(environments).isEqualTo(expectedResult);
    }

    @Test
    public void shouldWork3() {
        List<Environment> expectedResult = List.of(environment1, environment2, environment3, environment4);
        when(applicationRepository.getEnvironments()).thenReturn(expectedResult);

        List<Environment> environments = applicationService.getEnvironments();

        assertThat(environments).isEqualTo(expectedResult);
    }

    @Test
    public void shouldWork4() {
        List<Artifact> artifacts = applicationService.getArtifacts();
        assertThat(artifacts).isEmpty();
    }

    @Test
    public void shouldWork5() {
        List<Artifact> expectedResult = List.of(artifact1, artifact2, artifact3);
        when(applicationRepository.getArtifacts()).thenReturn(expectedResult);

        List<Artifact> artifacts = applicationService.getArtifacts();

        assertThat(artifacts).isEqualTo(expectedResult);
    }

    @Test
    public void shouldWork6() {
        Deployment deployment = applicationService.getDeploymentById(Integer.MAX_VALUE);

        assertThat(deployment).isNull();
    }

    @Test
    public void shouldWork7() {
        when(applicationRepository.getDeploymentById(1)).thenReturn(deployment1);

        Deployment deployment = applicationService.getDeploymentById(1);

        assertThat(deployment.getId()).isEqualTo(deployment1.getId());
        assertThat(deployment.getStatus()).isEqualTo(deployment1.getStatus());
        assertThat(deployment.getCreatedAt()).isEqualTo(deployment1.getCreatedAt());
        assertThat(deployment.getEnvironment()).isEqualTo(deployment1.getEnvironment());
        assertThat(deployment.getVersionedArtifact()).isEqualTo(deployment1.getVersionedArtifact());
        assertThat(deployment.getFinishedAt()).isEqualTo(deployment1.getFinishedAt());
    }

    @Test
    public void shouldWork8() {
        List<Deployment> deployments = applicationService.getDeploymentsByEnvironment(environment1);

        assertThat(deployments).isEmpty();
    }
}
