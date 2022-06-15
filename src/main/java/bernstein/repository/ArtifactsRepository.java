package bernstein.repository;

import bernstein.domain.Artifact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
// TODO: replace asterisks in SQL with explicit column names
public class ArtifactsRepository {

    private final JdbcTemplate jdbcTemplate;

    public ArtifactsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_ARTIFACTS_SQL = "SELECT name, version FROM artifacts";
    public List<Artifact> getArtifacts() {
        return jdbcTemplate.queryForList(GET_ARTIFACTS_SQL, Artifact.class);
    }

    private static final String GET_ARTIFACTS_BY_NAME_SQL = "SELECT name, version FROM artifacts WHERE name = ?";

    public List<Artifact> getArtifactsByName(String name) {
        return jdbcTemplate.queryForList(GET_ARTIFACTS_BY_NAME_SQL, Artifact.class, name);
    }

    private static final String GET_ARTIFACT_BY_NAME_AND_VERSION_SQL = "SELECT name, version FROM artifacts WHERE name = ? AND version = ?";

    public Artifact getArtifactByNameAndVersion(String name, String version) {
        return jdbcTemplate.queryForObject(GET_ARTIFACT_BY_NAME_AND_VERSION_SQL, Artifact.class, name, version);
    }

    private static final String INSERT_ARTIFACT_SQL = "INSERT INTO artifacts(name, version) VALUES(?, ?, ?)";

    public void insertArtifact(Artifact artifact) {
        jdbcTemplate.update(INSERT_ARTIFACT_SQL, artifact.getName(), artifact.getVersion());
    }
}
