package bernstein.api.request.body;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class PromoteRequestBody {
    int deploymentId;
    @NotNull
    String targetEnvironment;
    @NotNull
    String deployScript;
}
