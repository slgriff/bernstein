package bernstein.service.impl;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.repository.ApplicationRepository;
import bernstein.service.ApplicationService;
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
    public List<Artifact> getArtifacts() {
        return applicationRepository.getArtifacts();
    }

    @Override
    public List<Artifact> getArtifactsByEnvironment(Environment environment) {
        return applicationRepository.getArtifacts();
    }

    @Override
    public List<Environment> getEnvironments() {
        return applicationRepository.getEnvironments();
    }

    @Override
    public List<Deployment> getDeployments() {
        return applicationRepository.getDeployments();
    }

    @Override
    public List<Deployment> getDeploymentsByArtifact(Artifact artifact) {
        return applicationRepository.getDeploymentsByArtifact(artifact);
    }

    @Override
    public List<Deployment> getDeploymentsByEnvironment(Environment environment) {
        return applicationRepository.getDeploymentsByEnvironment(environment);
    }

    @Override
    public List<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return applicationRepository.getDeploymentsByEnvironmentAndArtifact(environment, artifact);
    }

    @Override
    public Deployment getDeploymentById(int id) {
        return null;
    }
}
