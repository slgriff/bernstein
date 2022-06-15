package bernstein.controller;

import bernstein.service.ArtifactsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ArtifactsController {

    private final ArtifactsService artifactsService;

    public ArtifactsController(ArtifactsService artifactsService) {
        this.artifactsService = artifactsService;
    }

    @GetMapping("/artifacts")
    public String getArtifacts(@RequestParam String environment) {
        return "artifacts";
    }

    @GetMapping("/artifact/{artifact}")
    public String getArtifact(@PathVariable String artifact, @RequestParam String environment) {
        return "artifact";
    }
}
