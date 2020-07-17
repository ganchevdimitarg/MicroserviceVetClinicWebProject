package d2g.vetclinicwebproject.web.api.models.guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
public class GuestApiControllerModel {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
