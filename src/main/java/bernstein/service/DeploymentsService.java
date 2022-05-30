package bernstein.service;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.repository.DeploymentsRepository;
import bernstein.task.DeploymentTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class DeploymentsService {

    private final TaskExecutor taskExecutor;

    private final DeploymentsRepository deploymentsRepository;

    public DeploymentsService(DeploymentsRepository deploymentsRepository, TaskExecutor taskExecutor) {
        this.deploymentsRepository = deploymentsRepository;
        this.taskExecutor = taskExecutor;
    }

    public void createDeploymentJob() {
        taskExecutor.execute(new DeploymentTask());
    }

    public Collection<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return deploymentsRepository.getDeploymentsByEnvironmentAndArtifact(environment, artifact);
    }

}
