package d2g.vetclinicwebproject.web.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterApiControllerModel {
    private String username;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
}


