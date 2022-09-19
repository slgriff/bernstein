package bernstein.repository.impl;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import bernstein.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ApplicationRepositoryImpl implements ApplicationRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String GET_ARTIFACTS_SQL = "SELECT name FROM artifacts";

    private static final String INSERT_ARTIFACT_SQL = "INSERT INTO artifacts(name) VALUES(?)";

    private static final String GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT_SQL =
            "SELECT d.id FROM deployments d"
                    + " WHERE d.environment_name = ? AND d.artifact_name = ?";

    private static final String GET_DEPLOYMENTS_BY_ENVIRONMENT_SQL =
            "SELECT d.id FROM deployments d"
                    + " WHERE d.environment_name = ?";

    private static final String GET_DEPLOYMENTS_BY_ARTIFACT_SQL =
            "SELECT d.id FROM deployments d"
                    + " WHERE d.artifact_name = ?";

    private static final String GET_DEPLOYMENTS_SQL = "SELECT id FROM deployments";

    private static final String INSERT_DEPLOYMENT_SQL = "INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)";

    private static final String GET_ENVIRONMENTS_SQL = "SELECT name FROM environments";

    private static final String GET_VERSIONED_ARTIFACTS_SQL = "SELECT name, version FROM versioned_artifacts";

    private static final String GET_VERSIONED_ARTIFACTS_BY_NAME_SQL = "SELECT name, version FROM versioned_artifacts WHERE name = ?";

    private static final String GET_VERSIONED_ARTIFACT_BY_NAME_AND_VERSION_SQL = "SELECT name, version FROM versioned_artifacts WHERE name = ? AND version = ?";

    private static final String INSERT_VERSIONED_ARTIFACT_SQL = "INSERT INTO versioned_artifacts(name, version) VALUES(?, ?)";

    private static final String INSERT_ENVIRONMENT_SQL = "INSERT INTO environments(name) VALUES(?)";

    public ApplicationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertArtifact(Artifact artifact) {
        jdbcTemplate.update(INSERT_ARTIFACT_SQL, artifact.getName());
    }

    @Override
    public void insertDeployment(Environment environment, VersionedArtifact versionedArtifact) {
        jdbcTemplate.update(INSERT_DEPLOYMENT_SQL, environment.getName(), versionedArtifact.getName(), versionedArtifact.getVersion());
    }

    @Override
    public void insertEnvironment(Environment environment) {
        jdbcTemplate.update(INSERT_ENVIRONMENT_SQL, environment.getName());
    }

    @Override
    public void insertVersionedArtifact(VersionedArtifact versionedArtifact) {
        jdbcTemplate.update(INSERT_VERSIONED_ARTIFACT_SQL, versionedArtifact.getName(), versionedArtifact.getVersion());
    }

    @Override
    public List<Artifact> getArtifacts() {
        return jdbcTemplate.query(GET_ARTIFACTS_SQL, (rs, i) -> new Artifact(rs.getString("name")));
    }

    @Override
    public List<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT_SQL,
                (rs, i) -> new Deployment(rs.getInt("id")),
                environment.getName(),
                artifact.getName());
    }

    @Override
    public List<Deployment> getDeploymentsByEnvironment(Environment environment) {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ENVIRONMENT_SQL, (rs, i) -> new Deployment(rs.getInt("id")), environment.getName());
    }

    @Override
    public List<Deployment> getDeploymentsByArtifact(Artifact artifact) {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ARTIFACT_SQL, (rs, i) -> new Deployment(rs.getInt("id")), artifact.getName());
    }

    @Override
    public List<Deployment> getDeployments() {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ARTIFACT_SQL, (rs, i) -> new Deployment(rs.getInt("id")));
    }

    @Override
    public List<Environment> getEnvironments() {
        return jdbcTemplate.query(GET_ENVIRONMENTS_SQL, (rs, i) -> new Environment(rs.getString("name")));
    }

    @Override
    public List<VersionedArtifact> getVersionedArtifacts() {
        return jdbcTemplate.queryForList(GET_VERSIONED_ARTIFACTS_SQL, VersionedArtifact.class);
    }

    @Override
    public List<VersionedArtifact> getVersionedArtifactsByName(String name) {
        return jdbcTemplate.queryForList(GET_VERSIONED_ARTIFACTS_BY_NAME_SQL, VersionedArtifact.class, name);
    }

    @Override
    public VersionedArtifact getVersionedArtifactByNameAndVersion(String name, String version) {
        return jdbcTemplate.queryForObject(GET_VERSIONED_ARTIFACT_BY_NAME_AND_VERSION_SQL, VersionedArtifact.class, name, version);
    }
}
