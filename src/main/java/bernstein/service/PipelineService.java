package bernstein.service;

import bernstein.pipeline.Phase;
import bernstein.pipeline.Pipeline;
import bernstein.pipeline.step.EchoStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PipelineService {

    public Pipeline getPipeline() {
        Pipeline pipeline = new Pipeline(
                List.of(new Phase(
                        List.of(new EchoStep())
                ))
        );
        return pipeline;
    }
}
