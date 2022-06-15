package bernstein.service;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.pipeline.Pipeline;
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

    private final PipelineService pipelineService;

    public DeploymentsService(DeploymentsRepository deploymentsRepository, TaskExecutor taskExecutor, PipelineService pipelineService) {
        this.deploymentsRepository = deploymentsRepository;
        this.taskExecutor = taskExecutor;
        this.pipelineService = pipelineService;
    }

    public void createDeploymentJob() {
        Pipeline pipeline = pipelineService.getPipeline();
        DeploymentTask deploymentTask = new DeploymentTask(pipeline);
        taskExecutor.execute(deploymentTask);
    }

    public Collection<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return deploymentsRepository.getDeploymentsByEnvironmentAndArtifact(environment, artifact);
    }

}
