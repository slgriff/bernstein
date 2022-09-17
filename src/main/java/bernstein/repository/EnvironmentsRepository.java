package bernstein.repository;

import bernstein.domain.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class EnvironmentsRepository {
    public static final String ENVIRONMENTS_CACHE_NAME = "environments";

    private final JdbcTemplate jdbcTemplate;

    public EnvironmentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_ENVIRONMENTS_SQL = "SELECT name FROM environments";

    @Cacheable(ENVIRONMENTS_CACHE_NAME)
    public List<Environment> getEnvironments() {
        return jdbcTemplate.query(GET_ENVIRONMENTS_SQL, (rs, i) -> new Environment(rs.getString("name")));
    }
}
