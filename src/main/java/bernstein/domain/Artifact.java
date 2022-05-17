package bernstein.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artifact {
    private String name;
    private String version;
}
