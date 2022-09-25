package bernstein.service.impl;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.domain.VersionedArtifact;
import bernstein.pipeline.Phase;
import bernstein.pipeline.Pipeline;
import bernstein.pipeline.step.EchoStep;
import bernstein.repository.ApplicationRepository;
import bernstein.service.ApplicationService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    @NonNull
    public List<Artifact> getArtifacts() {
        return applicationRepository.getArtifacts();
    }

    @Override
    @NonNull
    public List<Artifact> getArtifactsByEnvironment(@NonNull Environment environment) {
        return applicationRepository.getArtifacts();
    }

    @Override
    @NonNull
    public List<Environment> getEnvironments() {
        return applicationRepository.getEnvironments();
    }

    @Override
    @NonNull
    public List<Deployment> getDeployments() {
        return applicationRepository.getDeployments();
    }

    @Override
    @NonNull
    public List<Deployment> getDeploymentsByArtifact(@NonNull Artifact artifact) {
        return applicationRepository.getDeploymentsByArtifact(artifact);
    }

    @Override
    @NonNull
    public List<Deployment> getDeploymentsByEnvironment(@NonNull Environment environment) {
        return applicationRepository.getDeploymentsByEnvironment(environment);
    }

    @Override
    @NonNull
    public List<Deployment> getDeploymentsByEnvironmentAndArtifact(@NonNull Environment environment,
            @NonNull Artifact artifact) {
        return applicationRepository.getDeploymentsByEnvironmentAndArtifact(environment, artifact);
    }

    @Override
    @NonNull
    public Deployment getDeploymentById(int id) {
        return applicationRepository.getDeploymentById(id);
    }

    @Override
    public void promoteDeployment(@NonNull Deployment deployment) {
        if (deployment.getStatus() != Deployment.Status.SUCCESS) {
            return;
        }

        VersionedArtifact versionedArtifact = deployment.getVersionedArtifact();
        Environment environment = deployment.getEnvironment();

        deploy(versionedArtifact, environment);
    }

    @Override
    public void deploy(@NonNull VersionedArtifact versionedArtifact, @NonNull Environment environment) {

    }

    @Override
    @NonNull
    public Pipeline getPipeline() {
        Pipeline pipeline = new Pipeline(List.of(new Phase(List.of(new EchoStep()))));
        return pipeline;
    }

    @Override
    @Async
    @NonNull
    public Future<Integer> runPipeline(Pipeline pipeline) {
        log.info("test async method call");
        return new AsyncResult<>(pipeline.execute());
    }
}
