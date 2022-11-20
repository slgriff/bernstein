package bernstein.api.request.body;

import lombok.Value;
import javax.validation.constraints.NotNull;

@Value
public class DeployRequestBody {
    @NotNull
    String artifactName;
    @NotNull
    String artifactVersion;
    @NotNull
    String deployScript;
    @NotNull
    String targetEnvironment;
    @NotNull
    String downloadUrl;
}
