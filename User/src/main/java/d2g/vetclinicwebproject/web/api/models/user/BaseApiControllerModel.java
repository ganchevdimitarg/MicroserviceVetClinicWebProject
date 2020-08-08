package d2g.vetclinicwebproject.web.api.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static d2g.vetclinicwebproject.config.Constant.*;

@Getter
@Setter
@NoArgsConstructor
public class BaseApiControllerModel {
    @Size(min = 3, max = 20, message = INVALID_USERNAME_OR_PASSWORD_MASSAGE)
    private String username;
    private String password;
    private String confirmPassword;
}
