package bernstein.controller;

import bernstein.service.DeploymentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class DeploymentsController {

    private final DeploymentsService deploymentsService;

    public DeploymentsController(DeploymentsService deploymentsService) {
        this.deploymentsService = deploymentsService;
    }

    @GetMapping("/deployments")
    public String getDeployments(@RequestParam String environment, @RequestParam String artifact) {
        return "deployments";
    }

    @GetMapping("/deployment")
    public String getDeployment(@RequestParam Integer id) {
        return "deployment";
    }
}
