package bernstein.repository;

import bernstein.domain.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest
public class DeploymentsRepositoryTest {

    private static final String TEST_ARTIFACT_NAME1 = "test-artifact-for-deployment";

    private static final String TEST_ARTIFACT_VERSION1 = "";
    private static final String TEST_ARTIFACT_VERSION2 = "";
    private static final String TEST_ARTIFACT_VERSION3 = "";

    private final Artifact testArtifactv1 = Artifact.builder().name(TEST_ARTIFACT_NAME1).version(TEST_ARTIFACT_VERSION1).build();
    private final Artifact testArtifactv2 = Artifact.builder().name(TEST_ARTIFACT_NAME1).version(TEST_ARTIFACT_VERSION2).build();
    private final Artifact testArtifactv3 = Artifact.builder().name(TEST_ARTIFACT_NAME1).version(TEST_ARTIFACT_VERSION3).build();

    @Autowired
    private DeploymentsRepository deploymentsRepository;

    public void testA() {
    }


}
