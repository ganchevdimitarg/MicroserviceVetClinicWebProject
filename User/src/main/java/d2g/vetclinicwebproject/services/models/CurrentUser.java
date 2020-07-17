package d2g.vetclinicwebproject.services.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUser {
    private String username;

    public CurrentUser(String username) {
        this.username = username;
    }
}
