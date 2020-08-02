package d2g.vetclinicwebproject.web.api.models.guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static d2g.vetclinicwebproject.config.Constant.EMAIL_VALIDATE;
import static d2g.vetclinicwebproject.config.Constant.PHONE_NUMBER_VALIDATE;

@Getter
@Setter
@NoArgsConstructor
public class GuestUpdateApiControllerModel {
    private String id;
    private String username;
    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;
    @NotEmpty
    private String address;
    @Pattern(regexp = PHONE_NUMBER_VALIDATE)
    @NotEmpty
    private String phoneNumber;

    private MultipartFile image;
}