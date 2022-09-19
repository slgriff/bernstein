package bernstein.controller;

import bernstein.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class DeploymentsController {

    private final ApplicationService applicationService;

    public DeploymentsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/deployments")
    public String getDeployments(@RequestParam(required = false) String environment, @RequestParam(required = false) String artifact, Model model) {
        return "deployments";
    }

    @GetMapping("/deployment")
    public String getDeployment(@RequestParam Integer id, Model model) {
        return "deployment";
    }
}
