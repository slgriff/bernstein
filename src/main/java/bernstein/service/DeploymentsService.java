package bernstein.service;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.repository.DeploymentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class DeploymentsService {

    private final DeploymentsRepository deploymentsRepository;

    public DeploymentsService(DeploymentsRepository deploymentsRepository) {
        this.deploymentsRepository = deploymentsRepository;
    }

    public void createDeploymentJob() {

    }

    public Collection<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return deploymentsRepository.getDeploymentsByEnvironmentAndArtifact(environment, artifact);
    }

}
