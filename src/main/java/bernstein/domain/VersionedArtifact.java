package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class VersionedArtifact {
    @NonNull private String name;
    @NonNull private String version;

    @Builder
    public VersionedArtifact(@NonNull String name, @NonNull String version) {
        this.name = name;
        this.version = version;
    }
}
