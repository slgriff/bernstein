package bernstein.service.impl;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.repository.ApplicationRepository;
import bernstein.service.ApplicationService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
