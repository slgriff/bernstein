package bernstein.repository.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {
        "spring.datasource.url=jdbc:tc:postgresql:alpine:///bernstein" })
@RunWith(SpringRunner.class)
public class ApplicationRepositoryImplPostgresqlIT {

    @Test
    public void shouldWork1() {

    }
}
