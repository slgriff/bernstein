package bernstein.controller;

import bernstein.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class EnvironmentsController {

    private final ApplicationService applicationService;

    public EnvironmentsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/environments")
    public String getEnvironments(@RequestParam(required = false) String artifact, Model model) {
        return "environments";
    }

    @GetMapping("/environment/{environment}")
    public String getEnvironment(@PathVariable String environment, @RequestParam(required = false) String artifact, Model model) {
        return "environement";
    }
}
