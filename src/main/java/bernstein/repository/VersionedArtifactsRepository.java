package bernstein.repository;

import bernstein.domain.VersionedArtifact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class VersionedArtifactsRepository {
    public static final String VERSIONED_ARTIFACTS_CACHE_NAME = "versioned_artifacts";

    private final JdbcTemplate jdbcTemplate;

    public VersionedArtifactsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_VERSIONED_ARTIFACTS_SQL = "SELECT name, version FROM versioned_artifacts";

    @Cacheable(VERSIONED_ARTIFACTS_CACHE_NAME)
    public List<VersionedArtifact> getVersionedArtifacts() {
        return jdbcTemplate.queryForList(GET_VERSIONED_ARTIFACTS_SQL, VersionedArtifact.class);
    }

    private static final String GET_VERSIONED_ARTIFACTS_BY_NAME_SQL = "SELECT name, version FROM versioned_artifacts WHERE name = ?";

    @Cacheable(VERSIONED_ARTIFACTS_CACHE_NAME)
    public List<VersionedArtifact> getArtifactsByName(String name) {
        return jdbcTemplate.queryForList(GET_VERSIONED_ARTIFACTS_BY_NAME_SQL, VersionedArtifact.class, name);
    }

    private static final String GET_VERSIONED_ARTIFACT_BY_NAME_AND_VERSION_SQL = "SELECT name, version FROM versioned_artifacts WHERE name = ? AND version = ?";

    @Cacheable(VERSIONED_ARTIFACTS_CACHE_NAME)
    public VersionedArtifact getArtifactByNameAndVersion(String name, String version) {
        return jdbcTemplate.queryForObject(GET_VERSIONED_ARTIFACT_BY_NAME_AND_VERSION_SQL, VersionedArtifact.class, name, version);
    }

    private static final String INSERT_VERSIONED_ARTIFACT_SQL = "INSERT INTO versioned_artifacts(name, version) VALUES(?, ?)";

    public void insertVersionedArtifact(VersionedArtifact versionedArtifact) {
        jdbcTemplate.update(INSERT_VERSIONED_ARTIFACT_SQL, versionedArtifact.getName(), versionedArtifact.getVersion());
    }
}
