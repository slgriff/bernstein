package bernstein.repository;

import bernstein.domain.Artifact;
import bernstein.domain.Environment;
import bernstein.domain.Deployment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
// TODO: replace asterisks in SQL with explicit column names
public class DeploymentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public DeploymentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT_SQL =
            "SELECT id FROM deployments d JOIN (SELECT name FROM environments WHERE name = ?) e"
            + " ON d.environment_name = e.name"
            + " JOIN (SELECT name FROM artifacts WHERE name = ?) a ON d.artifact_name = a.name";


    @Cacheable("deployments")
    public List<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return jdbcTemplate.queryForList(GET_DEPLOYMENTS_BY_ENVIRONMENT_AND_ARTIFACT_SQL, Deployment.class, environment.getName(), artifact.getName());
    }

    private static final String INSERT_DEPLOYMENT_SQL = "INSERT INTO deployments(environment_name, artifact_name, artifact_version) VALUES(?, ?, ?)";

    public void insertDeployment(Environment environment, Artifact artifact) {
        jdbcTemplate.update(INSERT_DEPLOYMENT_SQL, environment.getName(), artifact.getName(), artifact.getVersion());
    }

    private static final String GET_DEPLOYMENT_BY_ID_SQL = "SELECT e.name, a.name FROM deployments d"
                                                           + " JOIN environments e ON d.environment_name = e.name"
                                                           + " JOIN artifacts a ON d.artifact_name = a.name"
                                                           + " WHERE d.id = ?";

    public Deployment getDeploymentById(Integer id) {
        return jdbcTemplate.queryForObject(GET_DEPLOYMENT_BY_ID_SQL, Deployment.class, id);
    }
}
