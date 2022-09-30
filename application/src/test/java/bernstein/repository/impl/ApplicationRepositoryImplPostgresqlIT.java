package bernstein.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource(properties = { "spring.datasource.url=jdbc:tc:postgresql:alpine:///bernstein" })
@RunWith(SpringRunner.class)
public class ApplicationRepositoryImplPostgresqlIT extends ApplicationRepositoryTestBase {
}
