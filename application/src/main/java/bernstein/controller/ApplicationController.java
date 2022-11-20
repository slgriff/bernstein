package bernstein.controller;

import bernstein.api.request.body.DeployRequestBody;
import bernstein.api.request.body.PromoteRequestBody;
import bernstein.domain.Deployment;
import bernstein.pipeline.Phase;
import bernstein.pipeline.Pipeline;
import bernstein.pipeline.step.DownloadArtifactStep;
import bernstein.pipeline.step.ShellStep;
import bernstein.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/deployments/promote")
    public ResponseEntity<String> promoteDeployment(@Valid @RequestBody PromoteRequestBody promoteRequestBody,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/deployments/deploy")
    public ResponseEntity<String> deploy(@Valid @RequestBody DeployRequestBody deployRequestBody,
            BindingResult bindingResult) {
        log.info("deploy={}", deployRequestBody);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        String script = deployRequestBody.getDeployScript();

        String downloadUrl = deployRequestBody.getDownloadUrl();

        Pipeline pipeline = new Pipeline();

        Phase phase = new Phase();
        phase.addStep(new DownloadArtifactStep(downloadUrl));
        phase.addStep(new ShellStep(script));
        pipeline.addPhase(phase);

        applicationService.runPipeline(pipeline);

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(1).toUri())
                .build();
    }

    @GetMapping("/deployment/{id}/status")
    public String getDeploymentStatus(@PathVariable("id") String id) {

        return "";
    }
}
