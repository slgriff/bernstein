package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.OffsetDateTime;

@Data
public class Deployment {
    private int id;
    @NonNull private Environment environment;
    @NonNull private VersionedArtifact versionedArtifact;
    @NonNull private OffsetDateTime createdAt;
    private OffsetDateTime finishedAt;

    @Builder
    public Deployment(int id,
                      @NonNull Environment environment,
                      @NonNull VersionedArtifact versionedArtifact,
                      @NonNull OffsetDateTime createdAt,
                      OffsetDateTime finishedAt) {
        this.id = id;
        this.environment = environment;
        this.versionedArtifact = versionedArtifact;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }
}
