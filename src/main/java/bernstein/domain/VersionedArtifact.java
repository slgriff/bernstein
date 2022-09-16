package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class VersionedArtifact {
    @NonNull private String name;
    @NonNull private String version;
}
