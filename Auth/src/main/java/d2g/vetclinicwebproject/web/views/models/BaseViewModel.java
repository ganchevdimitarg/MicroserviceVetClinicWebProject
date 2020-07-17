package d2g.vetclinicwebproject.web.views.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

import static d2g.vetclinicwebproject.config.Constant.INVALID_USERNAME_OR_PASSWORD_MASSAGE;
//import static d2g.vetclinicwebproject.config.Constant.PASSWORD_VALIDATE;

@Getter
@Setter
@NoArgsConstructor
public class BaseViewModel {
    @Size(min = 3, max = 20, message = INVALID_USERNAME_OR_PASSWORD_MASSAGE)
    private String username;
//    @Pattern(regexp = PASSWORD_VALIDATE, message = INVALID_USERNAME_OR_PASSWORD_MASSAGE)
    private String password;
    private String confirmPassword;
}
