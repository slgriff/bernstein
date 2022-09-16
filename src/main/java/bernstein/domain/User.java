package bernstein.domain;

import lombok.Builder;
import lombok.Data;
@Data
public class User {
    private int id;

    @Builder
    public User(int id) {
        this.id = id;
    }
}
