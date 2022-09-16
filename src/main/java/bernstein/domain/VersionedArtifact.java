package bernstein.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VersionedArtifact {
    private String name;
    private String version;
}
