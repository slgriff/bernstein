package bernstein.service;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import lombok.NonNull;

import java.util.List;

public interface ApplicationService {

    @NonNull
    List<Artifact> getArtifacts();

    @NonNull
    List<Artifact> getArtifactsByEnvironment(Environment environment);

    @NonNull
    List<Environment> getEnvironments();

    @NonNull
    List<Deployment> getDeployments();

    @NonNull
    List<Deployment> getDeploymentsByArtifact(@NonNull Artifact artifact);

    @NonNull
    List<Deployment> getDeploymentsByEnvironment(@NonNull Environment environment);

    @NonNull
    List<Deployment> getDeploymentsByEnvironmentAndArtifact(@NonNull Environment environment, @NonNull Artifact artifact);

    @NonNull
    Deployment getDeploymentById(int id);
}
