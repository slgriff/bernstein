package bernstein.service.impl;

import bernstein.pipeline.Phase;
import bernstein.pipeline.Pipeline;
import bernstein.pipeline.step.EchoStep;
import bernstein.service.PipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PipelineServiceImpl implements PipelineService {

    @Override
    public Pipeline getPipeline() {
        Pipeline pipeline = new Pipeline(List.of(new Phase(List.of(new EchoStep()))));
        return pipeline;
    }
}
