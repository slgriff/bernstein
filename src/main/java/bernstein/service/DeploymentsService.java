package bernstein.service;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.repository.DeploymentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public final class DeploymentsService {

    private static final String DEPLOYMENT_JOB_QUEUE_NAME = "deployment";

    private final JmsTemplate jmsTemplate;

    private final DeploymentsRepository deploymentsRepository;

    public DeploymentsService(JmsTemplate jmsTemplate, DeploymentsRepository deploymentsRepository) {
        this.jmsTemplate = jmsTemplate;
        this.deploymentsRepository = deploymentsRepository;
    }

    public void createDeploymentJob() {
        jmsTemplate.convertAndSend(DEPLOYMENT_JOB_QUEUE_NAME, Deployment.builder().build());
    }


    @JmsListener(destination = DEPLOYMENT_JOB_QUEUE_NAME)
    public void processMessage(Deployment deployment) {
        System.out.println("Process " + deployment);
    }

    public Collection<Deployment> getDeploymentsByEnvironmentAndArtifact(Environment environment, Artifact artifact) {
        return deploymentsRepository.getDeploymentsByEnvironmentAndArtifact(environment, artifact);
    }

}
