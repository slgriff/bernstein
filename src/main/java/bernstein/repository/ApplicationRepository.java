package bernstein.repository;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import lombok.NonNull;

import java.util.List;

public interface ApplicationRepository {
    void insertArtifact(Artifact artifact);
    void insertEnvironment(Environment environment);
    void insertVersionedArtifact(VersionedArtifact versionedArtifact);
    void insertDeployment(Environment environment, VersionedArtifact versionedArtifact);

    @NonNull
    List<Artifact> getArtifacts();

    @NonNull
    List<Environment> getEnvironments();

    @NonNull
    List<Deployment> getDeployments();

    Deployment getDeploymentById(int id);

    @NonNull
    List<Deployment> getDeploymentsByEnvironment(@NonNull Environment environment);

    @NonNull
    List<Deployment> getDeploymentsByArtifact(@NonNull Artifact artifact);

    @NonNull
    List<Deployment> getDeploymentsByEnvironmentAndArtifact(@NonNull Environment environment, @NonNull Artifact artifact);

    @NonNull
    List<VersionedArtifact> getVersionedArtifacts();

    @NonNull
    List<VersionedArtifact> getVersionedArtifactsByName(@NonNull String name);

    VersionedArtifact getVersionedArtifactByNameAndVersion(@NonNull String name, @NonNull String version);
}
