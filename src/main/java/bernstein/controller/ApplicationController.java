package bernstein.controller;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.domain.Environment;
import bernstein.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/artifacts")
    public String getArtifacts(@RequestParam(required = false) String environment, Model model) {
        List<Artifact> artifacts;
        if (environment == null || environment.isBlank()) {
            artifacts = applicationService.getArtifacts();
        } else {
            artifacts = applicationService.getArtifactsByEnvironment(new Environment(environment));
        }
        return "artifacts";
    }

    @GetMapping("/artifact/{artifact}")
    public String getArtifact(@PathVariable String artifact, @RequestParam(required = false) String environment, Model model) {
        Artifact oArtifact = new Artifact(artifact);
        if (environment == null || environment.isBlank()) {
            model.addAttribute("", "");
        } else {
            model.addAttribute("", "");
        }
        return "artifact";
    }

    @GetMapping("/deployments")
    public String getDeployments(@RequestParam(required = false) String environment, @RequestParam(required = false) String artifact, Model model) {
        List<Deployment> deployments;

        boolean specificEnvironment = environment != null && !environment.isBlank();
        boolean specificArtifact = artifact != null && !artifact.isBlank();

        if (specificEnvironment && specificArtifact) {
            deployments = applicationService.getDeploymentsByEnvironmentAndArtifact(new Environment(environment), new Artifact(artifact));
        } else if (specificEnvironment) {
            deployments = applicationService.getDeploymentsByEnvironment(new Environment(environment));
        } else if (specificArtifact) {
            deployments = applicationService.getDeploymentsByArtifact(new Artifact(artifact));
        } else {
            deployments = applicationService.getDeployments();
        }

        model.addAttribute("deployments", deployments);

        return "deployments";
    }

    @GetMapping("/deployment")
    public String getDeployment(@RequestParam Integer id, Model model) {
        Deployment deployment = applicationService.getDeploymentById(id);
        model.addAttribute("deployment", deployment);
        return "deployment";
    }

    @GetMapping("/environments")
    public String getEnvironments(@RequestParam(required = false) String artifact, Model model) {
        List<Deployment> deployments;

        if (artifact == null || artifact.isBlank()) {
            deployments = applicationService.getDeployments();
        } else {
            deployments = applicationService.getDeploymentsByArtifact(new Artifact(artifact));
        }

        return "environments";
    }

    @GetMapping("/environment/{environment}")
    public String getEnvironment(@PathVariable String environment, @RequestParam(required = false) String artifact, Model model) {
        Environment oEnvironment = new Environment(environment);

        List<Deployment> deployments;

        if (artifact == null || artifact.isBlank()) {
            deployments = applicationService.getDeploymentsByEnvironment(oEnvironment);
        } else {
            Artifact oArtifact = new Artifact(artifact);
            deployments = applicationService.getDeploymentsByEnvironmentAndArtifact(oEnvironment, oArtifact);
        }

        model.addAttribute("environment", oEnvironment);
        model.addAttribute("deployments", deployments);

        return "environment";
    }

    @ModelAttribute("environments")
    public List<Environment> populateEnvironments() {
        return applicationService.getEnvironments();
    }
}
