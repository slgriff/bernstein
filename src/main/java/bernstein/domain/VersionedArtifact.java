package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.OffsetDateTime;

@Data
public class VersionedArtifact {
    @NonNull private String name;
    @NonNull private String version;
    @NonNull private OffsetDateTime createdAt;

    @Builder
    public VersionedArtifact(@NonNull String name, @NonNull String version, @NonNull OffsetDateTime createdAt) {
        this.name = name;
        this.version = version;
        this.createdAt = createdAt;
    }
}
