package bernstein.controller;

import bernstein.service.EnvironmentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class EnvironmentsController {

    private final EnvironmentsService environmentsService;

    public EnvironmentsController(EnvironmentsService environmentsService) {
        this.environmentsService = environmentsService;
    }

    @GetMapping("/environments")
    public String getEnvironments(@RequestParam String artifact) {
        return "environments";
    }

    @GetMapping("/environment/{environment}")
    public String getEnvironment(@PathVariable String environment, @RequestParam String artifact) {
        return "environement";
    }
}
