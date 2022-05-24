package bernstein.controller;

import bernstein.service.DeploymentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class DeploymentsController {

    private final DeploymentsService deploymentsService;

    public DeploymentsController(DeploymentsService deploymentsService) {
        this.deploymentsService = deploymentsService;
    }

    @GetMapping("/deployments/{environment}/{artifact}")
    public String getDeployments(@PathVariable(required = false) String environment, @PathVariable(required = false) String artifact) {
        if (environment != null) {
            if (artifact != null) {
                return "";
            } else {
                return "";
            }
        }
        return "";
    }
}
