package bernstein.repository.impl;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import bernstein.repository.ApplicationRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class ApplicationRepositoryImpl implements ApplicationRepository {
    private final JdbcTemplate jdbcTemplate;

    public ApplicationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertArtifact(@NonNull Artifact artifact) {
        String sql = "INSERT INTO artifacts(name) VALUES(?)";
        jdbcTemplate.update(sql, artifact.getName());
    }

    @Override
    public int insertDeployment(@NonNull Environment environment, @NonNull VersionedArtifact versionedArtifact) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, environment.getName());
            preparedStatement.setString(2, versionedArtifact.getName());
            preparedStatement.setString(3, versionedArtifact.getVersion());
            return preparedStatement;
        }, keyHolder);

        return (int) keyHolder.getKey();

        // jdbcTemplate.update(sql, environment.getName(), versionedArtifact.getName(), versionedArtifact.getVersion());
    }

    @Override
    public void insertEnvironment(@NonNull Environment environment) {
        String sql = "INSERT INTO environments(name) VALUES(?)";
        jdbcTemplate.update(sql, environment.getName());
    }

    @Override
    public void insertVersionedArtifact(@NonNull VersionedArtifact versionedArtifact) {
        String sql = "INSERT INTO versioned_artifacts(artifact_name, artifact_version) VALUES(?, ?)";
        jdbcTemplate.update(sql, versionedArtifact.getName(), versionedArtifact.getVersion());
    }

    @Override
    @NonNull
    public List<Artifact> getArtifacts() {
        String sql = "SELECT name FROM artifacts";
        return jdbcTemplate.query(sql, new ArtifactRowMapper());
    }

    @Override
    @NonNull
    public List<Deployment> getDeploymentsByEnvironmentAndArtifact(@NonNull Environment environment,
            @NonNull Artifact artifact) {
        String sql = "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
                + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
                + " FROM deployments d" + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)"
                + " WHERE d.environment_name = ? AND d.artifact_name = ?";
        return jdbcTemplate.query(sql, new DeploymentRowMapper(), environment.getName(), artifact.getName());
    }

    @Override
    @NonNull
    public List<Deployment> getDeploymentsByEnvironment(@NonNull Environment environment) {
        String sql = "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
                + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
                + " FROM deployments d" + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)"
                + " WHERE d.environment_name = ?";
        return jdbcTemplate.query(sql, new DeploymentRowMapper(), environment.getName());
    }

    @Override
    public List<Deployment> getDeploymentsByArtifact(Artifact artifact) {
        String sql = "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
                + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
                + " FROM deployments d" + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)"
                + " WHERE d.artifact_name = ?";
        return jdbcTemplate.query(sql, new DeploymentRowMapper(), artifact.getName());
    }

    @Override
    @NonNull
    public List<Deployment> getDeployments() {
        String sql = "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
                + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
                + " FROM deployments d" + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)";
        return jdbcTemplate.query(sql, new DeploymentRowMapper());
    }

    @Override
    @NonNull
    public List<Environment> getEnvironments() {
        String sql = "SELECT name FROM environments";
        return jdbcTemplate.query(sql, new EnvironmentRowMapper());
    }

    @Override
    @NonNull
    public List<VersionedArtifact> getVersionedArtifacts() {
        String sql = "SELECT artifact_name, artifact_version, created_at FROM versioned_artifacts";
        return jdbcTemplate.query(sql, new VersionedArtifactRowMapper());
    }

    @Override
    @NonNull
    public List<VersionedArtifact> getVersionedArtifactsByName(@NonNull String name) {
        String sql = "SELECT artifact_name, artifact_version, created_at FROM versioned_artifacts WHERE artifact_name = ?";
        return jdbcTemplate.query(sql, new VersionedArtifactRowMapper(), name);
    }

    @Override
    public VersionedArtifact getVersionedArtifactByNameAndVersion(@NonNull String name, @NonNull String version) {
        String sql = "SELECT artifact_name, artifact_version, created_at FROM versioned_artifacts WHERE artifact_name = ? AND artifact_version = ?";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return new VersionedArtifact(rs.getString("artifact_name"), rs.getString("artifact_version"),
                        rs.getTimestamp("created_at"));
            } else {
                throw new DataRetrievalFailureException("Could not find versioned artifact with given parameters.");
            }
        }, name, version);
    }

    @Override
    public Deployment getDeploymentById(int id) {
        String sql = "SELECT d.id AS deployment_id, d.environment_name, d.artifact_name, d.artifact_version, d.status,"
                + " d.created_at AS deployment_created_at, d.finished_at, va.created_at AS versioned_artifact_created_at"
                + " FROM deployments d" + " JOIN versioned_artifacts va USING (artifact_name, artifact_version)"
                + " WHERE d.id = id";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return new Deployment(rs.getInt("deployment_id"), new Environment(rs.getString("environment_name")),
                        new VersionedArtifact(rs.getString("artifact_name"), rs.getString("artifact_version"),
                                rs.getTimestamp("versioned_artifact_created_at")),
                        Deployment.Status.valueOf(rs.getString("status")), rs.getTimestamp("deployment_created_at"),
                        rs.getTimestamp("finished_at"));
            } else {
                throw new DataRetrievalFailureException("Could not find deployment with given parameters.");
            }
        }, id);
    }

    private static class DeploymentRowMapper implements RowMapper<Deployment> {
        @Override
        public Deployment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Deployment(rs.getInt("deployment_id"), new Environment(rs.getString("environment_name")),
                    new VersionedArtifact(rs.getString("artifact_name"), rs.getString("artifact_version"),
                            rs.getTimestamp("versioned_artifact_created_at")),
                    Deployment.Status.valueOf(rs.getString("status")), rs.getTimestamp("deployment_created_at"),
                    rs.getTimestamp("finished_at"));
        }
    }

    private static class EnvironmentRowMapper implements RowMapper<Environment> {
        @Override
        public Environment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Environment(rs.getString("name"));
        }
    }

    private static class ArtifactRowMapper implements RowMapper<Artifact> {
        @Override
        public Artifact mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Artifact(rs.getString("name"));
        }
    }

    private static class VersionedArtifactRowMapper implements RowMapper<VersionedArtifact> {
        @Override
        public VersionedArtifact mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new VersionedArtifact(rs.getString("artifact_name"), rs.getString("artifact_version"),
                    rs.getTimestamp("created_at"));
        }
    }
}
