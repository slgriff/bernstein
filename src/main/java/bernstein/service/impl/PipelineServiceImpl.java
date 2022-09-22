package bernstein.service.impl;

import bernstein.pipeline.Phase;
import bernstein.pipeline.Pipeline;
import bernstein.pipeline.step.EchoStep;
import bernstein.service.PipelineService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class PipelineServiceImpl implements PipelineService {

    @Override
    @NonNull
    public Pipeline getPipeline() {
        Pipeline pipeline = new Pipeline(List.of(new Phase(List.of(new EchoStep()))));
        return pipeline;
    }

    @Override
    @Async
    @NonNull
    public Future<Integer> runPipeline(Pipeline pipeline) {
        log.info("test async method call");
        return new AsyncResult<>(pipeline.execute());
    }
}
