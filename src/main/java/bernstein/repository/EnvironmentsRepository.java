package bernstein.repository;

import bernstein.domain.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
// TODO: replace asterisks in SQL with explicit column names
public class EnvironmentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnvironmentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_ENVIRONMENTS_SQL = "SELECT name FROM environments";

    @Cacheable("environments")
    public List<Environment> getEnvironments() {
        return jdbcTemplate.queryForList(GET_ENVIRONMENTS_SQL, Environment.class);
    }
}
