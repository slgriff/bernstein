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
import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.RunNodesException;
import org.jclouds.compute.domain.ExecResponse;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.Template;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.sshj.config.SshjSshClientModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    // @Value("${jclouds.provider}")
    // private String jcloudsProvider;

    // @Value("${imageId")
    // private String imageId;

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
    public Future<Boolean> runPipeline(Pipeline pipeline) {
        log.trace("test asynch method call");

        try (ComputeServiceContext context = ContextBuilder.newBuilder("docker")
                .modules(Set.of(new SLF4JLoggingModule(), new SshjSshClientModule()))
                .buildView(ComputeServiceContext.class)) {

            ComputeService client = context.getComputeService();

            Template template = client.templateBuilder()
                    .imageId("b945ea1bf94d5f48947931a93a847334480cd097bed7fee961c00c165a5a9e18").build();

            Set<? extends NodeMetadata> nodes = client.createNodesInGroup("bernstein", 1, template);

            for (NodeMetadata node : nodes) {
                ExecResponse response = client.runScriptOnNode(node.getId(), "echo HELLO;\necho WORLD");
                log.info(response.toString());
            }

            return new AsyncResult<>(true);
        } catch (RunNodesException e) {
            log.error("", e);

            return new AsyncResult<>(false);
        }

    }
}
