package bernstein.controller;

import bernstein.domain.Artifact;
import bernstein.domain.Deployment;
import bernstein.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/deployments")
    public String getAllDeployments(Model model) {

        List<Deployment> deployments = applicationService.getDeployments();
        model.addAttribute("allDeployments", deployments);

        List<Artifact> artifacts = applicationService.getArtifacts();
        model.addAttribute("allArtifacts", artifacts);

        return "deployments";
    }

    @PostMapping("/deployments/promote")
    public String promoteDeployment(@RequestParam Integer deploymentId) {

        return "";
    }

    @PostMapping("/deployments/deploy")
    public String deploy(@RequestBody DeployRequest deployRequest) {

        return "";
    }

    @GetMapping("/deployment/{id}")
    public String getDeployment(@PathVariable Integer id, @RequestParam(required = false) Boolean promote,
            Model model) {
        Deployment deployment = applicationService.getDeploymentById(id);
        model.addAttribute("deployment", deployment);
        return "deployment";
    }

    static class DeployRequest {

    }
}
