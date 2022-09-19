package bernstein.repository;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;

import java.util.List;

public interface ApplicationRepository {
    void insertArtifact(Artifact artifact);
    void insertEnvironment(Environment environment);
    void insertVersionedArtifact(VersionedArtifact versionedArtifact);
    void insertDeployment(Environment environment, VersionedArtifact versionedArtifact);

    List<Artifact> getArtifacts();
    List<Environment> getEnvironments();

    List<Deployment> getDeployments();
    List<Deployment> getDeploymentsByEnvironment(Environment environment);
    List<Deployment> getDeploymentsByArtifact(Artifact artifact);
    List<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact);


    List<VersionedArtifact> getVersionedArtifacts();
    List<VersionedArtifact> getVersionedArtifactsByName(String name);
    VersionedArtifact getVersionedArtifactByNameAndVersion(String name, String version);
}
