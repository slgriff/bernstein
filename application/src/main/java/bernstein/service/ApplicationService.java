package bernstein.service;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import bernstein.pipeline.Pipeline;
import lombok.NonNull;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.Future;

public interface ApplicationService {

    @NonNull
    List<Artifact> getArtifacts();

    @NonNull
    List<Artifact> getArtifactsByEnvironment(@NonNull Environment environment);

    @NonNull
    List<Environment> getEnvironments();

    @NonNull
    List<Deployment> getDeployments();

    @NonNull
    List<Deployment> getDeploymentsByArtifact(@NonNull Artifact artifact);

    @NonNull
    List<Deployment> getDeploymentsByEnvironment(@NonNull Environment environment);

    @NonNull
    List<Deployment> getDeploymentsByEnvironmentAndArtifact(@NonNull Environment environment,
            @NonNull Artifact artifact);

    @NonNull
    Deployment getDeploymentById(int id);

    void promoteDeployment(@NonNull Deployment deployment);

    @NonNull
    Pipeline getPipeline();

    @Async
    @NonNull
    Future<Integer> runPipeline(Pipeline pipeline);

    void deploy(@NonNull VersionedArtifact versionedArtifact, @NonNull Environment environment);
}
