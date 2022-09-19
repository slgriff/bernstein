package bernstein.controller;

import bernstein.domain.Environment;
import bernstein.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "artifacts";
    }

    @GetMapping("/artifact/{artifact}")
    public String getArtifact(@PathVariable String artifact, @RequestParam(required = false) String environment) {
        return "artifact";
    }

    @GetMapping("/deployments")
    public String getDeployments(@RequestParam(required = false) String environment, @RequestParam(required = false) String artifact, Model model) {
        return "deployments";
    }

    @GetMapping("/deployment")
    public String getDeployment(@RequestParam Integer id, Model model) {
        return "deployment";
    }

    @GetMapping("/environments")
    public String getEnvironments(@RequestParam(required = false) String artifact, Model model) {
        //model.addAttribute("environments", applicationService.getEnvironments());
        model.addAttribute("environments", List.of(
                new Environment("abcd"),
                new Environment("efgh"),
                new Environment("ijkl")
        ));
        return "environments";
    }

    @GetMapping("/environment/{environment}")
    public String getEnvironment(@PathVariable String environment, @RequestParam(required = false) String artifact, Model model) {
        return "environement";
    }
}
