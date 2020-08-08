package d2g.vetclinicwebproject.web.api.models.guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
public class GuestApiControllerModel {
    @Email
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
