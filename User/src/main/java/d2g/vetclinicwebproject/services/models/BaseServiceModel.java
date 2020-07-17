package d2g.vetclinicwebproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseServiceModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String logInKey;
}
