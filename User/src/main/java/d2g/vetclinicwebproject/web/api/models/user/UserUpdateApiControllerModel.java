package d2g.vetclinicwebproject.web.api.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static d2g.vetclinicwebproject.config.Constant.EMAIL_VALIDATE;
import static d2g.vetclinicwebproject.config.Constant.PHONE_NUMBER_VALIDATE;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateApiControllerModel {
    private String id;
    private String username;
    @NotEmpty
    private String name;
    @Pattern(regexp = EMAIL_VALIDATE)
    @NotEmpty
    private String email;
    @NotEmpty
    private String address;
    @Pattern(regexp = PHONE_NUMBER_VALIDATE)
    @NotEmpty
    private String phoneNumber;
    private String imageUrl;
}