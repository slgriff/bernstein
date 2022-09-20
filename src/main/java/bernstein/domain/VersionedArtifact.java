package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class VersionedArtifact {
    @NonNull private String name;
    @NonNull private String version;
    @NonNull private Timestamp createdAt;

    @Builder
    public VersionedArtifact(@NonNull String name, @NonNull String version, @NonNull Timestamp createdAt) {
        this.name = name;
        this.version = version;
        this.createdAt = createdAt;
    }
}
