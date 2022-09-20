package bernstein.repository.impl;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import bernstein.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class ApplicationRepositoryImpl implements ApplicationRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String GET_ARTIFACTS_SQL =
            "SELECT name FROM artifacts";

    private static final String INSERT_ARTIFACT_SQL =
            "INSERT INTO artifacts(name) VALUES(?)";

    private static final String GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT_SQL =
            "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
          + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
          + " FROM deployments d"
          + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)"
          + " WHERE d.environment_name = ? AND d.artifact_name = ?";

    private static final String GET_DEPLOYMENTS_BY_ENVIRONMENT_SQL =
            "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
                    + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
                    + " FROM deployments d"
                    + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)"
          + " WHERE d.environment_name = ?";

    private static final String GET_DEPLOYMENTS_BY_ARTIFACT_SQL =
            "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
                    + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
                    + " FROM deployments d"
                    + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)"
          + " WHERE d.artifact_name = ?";

    private static final String GET_DEPLOYMENTS_SQL =
            "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
                    + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
                    + " FROM deployments d"
                    + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)";

    private static final String INSERT_DEPLOYMENT_SQL =
            "INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)";

    private static final String GET_ENVIRONMENTS_SQL =
            "SELECT name FROM environments";

    private static final String GET_VERSIONED_ARTIFACTS_SQL =
            "SELECT artifact_name, artifact_version, created_at FROM versioned_artifacts";

    private static final String GET_VERSIONED_ARTIFACTS_BY_NAME_SQL =
            "SELECT artifact_name, artifact_version, created_at FROM versioned_artifacts WHERE artifact_name = ?";

    private static final String GET_VERSIONED_ARTIFACT_BY_NAME_AND_VERSION_SQL =
            "SELECT artifact_name, artifact_version, created_at FROM versioned_artifacts WHERE artifact_name = ? AND artifact_version = ?";

    private static final String INSERT_VERSIONED_ARTIFACT_SQL =
            "INSERT INTO versioned_artifacts(artifact_name, artifact_version) VALUES(?, ?)";

    private static final String INSERT_ENVIRONMENT_SQL =
            "INSERT INTO environments(name) VALUES(?)";

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
        return jdbcTemplate.query(GET_ARTIFACTS_SQL, new ArtifactRowMapper());
    }

    @Override
    public List<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return jdbcTemplate.query(
                GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT_SQL,
                new DeploymentRowMapper(),
                environment.getName(),
                artifact.getName());
    }

    @Override
    public List<Deployment> getDeploymentsByEnvironment(Environment environment) {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ENVIRONMENT_SQL, new DeploymentRowMapper(), environment.getName());
    }

    @Override
    public List<Deployment> getDeploymentsByArtifact(Artifact artifact) {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ARTIFACT_SQL, new DeploymentRowMapper(), artifact.getName());
    }

    @Override
    public List<Deployment> getDeployments() {
        return jdbcTemplate.query(GET_DEPLOYMENTS_SQL, new DeploymentRowMapper());
    }

    @Override
    public List<Environment> getEnvironments() {
        return jdbcTemplate.query(GET_ENVIRONMENTS_SQL, new EnvironmentRowMapper());
    }

    @Override
    public List<VersionedArtifact> getVersionedArtifacts() {
        return jdbcTemplate.query(GET_VERSIONED_ARTIFACTS_SQL, new VersionedArtifactRowMapper());
    }

    @Override
    public List<VersionedArtifact> getVersionedArtifactsByName(String name) {
        return jdbcTemplate.query(GET_VERSIONED_ARTIFACTS_BY_NAME_SQL, new VersionedArtifactRowMapper(), name);
    }

    @Override
    public VersionedArtifact getVersionedArtifactByNameAndVersion(String name, String version) {
        return jdbcTemplate.queryForObject(GET_VERSIONED_ARTIFACT_BY_NAME_AND_VERSION_SQL, VersionedArtifact.class, name, version);
    }

    private class DeploymentRowMapper implements RowMapper<Deployment> {
        @Override
        public Deployment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Deployment(
                    rs.getInt("deployment_id"),
                    new Environment(rs.getString("environment_name")),
                    new VersionedArtifact(
                            rs.getString("artifact_name"),
                            rs.getString("artifact_version"),
                            rs.getTimestamp("versioned_artifact_created_at")
                    ),
                    Deployment.Status.valueOf(rs.getString("status")),
                    rs.getTimestamp("deployment_created_at"),
                    rs.getTimestamp("finished_at"));
        }
    }

    private class EnvironmentRowMapper implements RowMapper<Environment> {
        @Override
        public Environment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Environment(
                    rs.getString("name")
            );
        }
    }

    private class ArtifactRowMapper implements RowMapper<Artifact> {
        @Override
        public Artifact mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Artifact(
                    rs.getString("name")
            );
        }
    }

    private class VersionedArtifactRowMapper implements RowMapper<VersionedArtifact> {
        @Override
        public VersionedArtifact mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new VersionedArtifact(
                    rs.getString("artifact_name"),
                    rs.getString("artifact_version"),
                    rs.getTimestamp("created_at")
            );
        }
    }
}
