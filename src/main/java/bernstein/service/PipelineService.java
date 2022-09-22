package bernstein.service;

import bernstein.pipeline.Pipeline;
import lombok.NonNull;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface PipelineService {

    @NonNull
    Pipeline getPipeline();

    @Async
    @NonNull
    Future<Integer> runPipeline(Pipeline pipeline);
}
