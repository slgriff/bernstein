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
public class ArtifactsController {

    private final ApplicationService applicationService;

    public ArtifactsController(ApplicationService applicationService) {
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
}
