package bernstein.repository;

import bernstein.domain.Artifact;
import bernstein.domain.Environment;
import bernstein.domain.Deployment;
import bernstein.domain.VersionedArtifact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class DeploymentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public DeploymentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT_SQL =
            "SELECT d.id FROM deployments d"
            + " WHERE d.environment_name = ? AND d.artifact_name = ?";


    @Cacheable("deployments")
    public List<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT_SQL,
                (rs, i) -> new Deployment(rs.getInt("id")),
                environment.getName(),
                artifact.getName());
    }

    private static final String GET_DEPLOYMENTS_BY_ENVIRONMENT_SQL =
            "SELECT d.id FROM deployments d"
            + " WHERE d.environment_name = ?";

    @Cacheable("deployments")
    public List<Deployment> getDeploymentsByEnvironment(Environment environment) {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ENVIRONMENT_SQL, (rs, i) -> new Deployment(rs.getInt("id")), environment.getName());
    }

    private static final String GET_DEPLOYMENTS_BY_ARTIFACT_SQL =
            "SELECT d.id FROM deployments d"
            + " WHERE d.artifact_name = ?";

    @Cacheable("deployments")
    public List<Deployment> getDeploymentsByArtifact(Artifact artifact) {
        return jdbcTemplate.query(GET_DEPLOYMENTS_BY_ARTIFACT_SQL, (rs, i) -> new Deployment(rs.getInt("id")), artifact.getName());
    }

    private static final String INSERT_DEPLOYMENT_SQL = "INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)";

    public void insertDeployment(Environment environment, VersionedArtifact versionedArtifact) {
        jdbcTemplate.update(INSERT_DEPLOYMENT_SQL, environment.getName(), versionedArtifact.getName(), versionedArtifact.getVersion());
    }
}
