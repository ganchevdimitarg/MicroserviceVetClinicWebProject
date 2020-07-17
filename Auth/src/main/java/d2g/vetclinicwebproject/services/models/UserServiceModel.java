package d2g.vetclinicwebproject.services.models;

import d2g.vetclinicwebproject.data.model.AuthorityEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {
    private String id;
    private String username;
    private String password;
    private boolean enabled;
    private List<AuthorityEntity> authorities;
}


