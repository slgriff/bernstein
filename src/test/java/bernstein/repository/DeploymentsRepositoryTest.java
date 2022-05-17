package bernstein.repository;

import bernstein.domain.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest
public class DeploymentsRepositoryTest {

    private final Artifact testArtifact1 = Artifact.builder().name("test-").version("0.0.1").build();

    @Autowired
    private DeploymentsRepository deploymentsRepository;

    public void testA() {
        //
    }


}
