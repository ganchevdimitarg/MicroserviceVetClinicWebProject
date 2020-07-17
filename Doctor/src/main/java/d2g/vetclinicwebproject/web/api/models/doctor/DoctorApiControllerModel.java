package d2g.vetclinicwebproject.web.api.models.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static d2g.vetclinicwebproject.config.Constant.INVALID_TEXT_LENGTH_MASSAGE;

@Getter
@Setter
@NoArgsConstructor
public class DoctorApiControllerModel {
    private String id;
    @NotEmpty
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String name;
    @NotEmpty
    @Size(min = 3, max = 6, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String specialization;
    @NotEmpty
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String description;
}
