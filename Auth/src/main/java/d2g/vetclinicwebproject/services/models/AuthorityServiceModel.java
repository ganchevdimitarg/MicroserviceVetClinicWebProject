package d2g.vetclinicwebproject.services.models;

import d2g.vetclinicwebproject.data.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityServiceModel{
    private String id;
    private String name;
    private UserEntity user;
}
