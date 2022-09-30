package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class User {
    @NonNull
    private UUID id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    private boolean enabled;
    @NonNull
    private Timestamp createdAt;

    @Builder
    public User(@NonNull UUID id, @NonNull String username, @NonNull String password, boolean enabled,
            @NonNull Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }
}
