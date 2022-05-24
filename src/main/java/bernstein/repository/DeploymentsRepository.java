package bernstein.repository;

import bernstein.domain.Artifact;
import bernstein.domain.Environment;
import bernstein.domain.Deployment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Slf4j
public class DeploymentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public DeploymentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT =
            "SELECT * FROM DEPLOYMENTS d JOIN (SELECT * FROM ENVIRONMENTS WHERE name = ?) e"
            + " ON d.environment_name = e.name"
            + " JOIN (SELECT * FROM ARTIFACTS WHERE name = ?) a ON d.artifact_name = a.name";


    public Collection<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return jdbcTemplate.queryForList(GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT, Deployment.class, environment.getName(), artifact.getName());
    }

    private static final String INSERT_DEPLOYMENT = "INSERT INTO DEPLOYMENTS(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)";

    public void insertDeployment(Environment environment, Artifact artifact) {
        jdbcTemplate.update(INSERT_DEPLOYMENT, environment.getName(), artifact.getName(), artifact.getVersion());
    }
}
