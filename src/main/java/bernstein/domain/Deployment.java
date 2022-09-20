package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class Deployment {
    private int id;
    @NonNull private Environment environment;
    @NonNull private VersionedArtifact versionedArtifact;
    @NonNull private Status status;
    @NonNull private Timestamp createdAt;
    private Timestamp finishedAt;

    @Builder
    public Deployment(int id,
                      @NonNull Environment environment,
                      @NonNull VersionedArtifact versionedArtifact,
                      @NonNull Status status,
                      @NonNull Timestamp createdAt,
                      Timestamp finishedAt) {
        this.id = id;
        this.environment = environment;
        this.versionedArtifact = versionedArtifact;
        this.status = status;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }

    public enum Status {
        SUCCESS,
        FAILURE,
        IN_PROGRESS,
        CANCELLED
    }
}
