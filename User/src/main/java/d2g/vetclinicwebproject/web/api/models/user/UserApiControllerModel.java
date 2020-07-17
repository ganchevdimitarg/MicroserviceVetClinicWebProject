package d2g.vetclinicwebproject.web.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserApiControllerModel {
    private String id;
    private String username;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
