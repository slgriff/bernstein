package bernstein.repository;

import bernstein.domain.Artifact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ArtifactsRepository {
    public static final String ARTIFACTS_CACHE_NAME = "artifacts";

    private final JdbcTemplate jdbcTemplate;

    public ArtifactsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_ARTIFACTS_SQL = "SELECT name FROM artifacts";
    @Cacheable(ARTIFACTS_CACHE_NAME)
    public List<Artifact> getArtifacts() {
        return jdbcTemplate.query(GET_ARTIFACTS_SQL, (rs, i) -> new Artifact(rs.getString("name")));
    }

    private static final String INSERT_ARTIFACT_SQL = "INSERT INTO artifacts(name) VALUES(?)";

    public void insertArtifact(Artifact artifact) {
        jdbcTemplate.update(INSERT_ARTIFACT_SQL, artifact.getName());
    }
}
