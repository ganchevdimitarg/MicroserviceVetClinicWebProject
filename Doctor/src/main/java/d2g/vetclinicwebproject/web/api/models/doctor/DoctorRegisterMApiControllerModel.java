package d2g.vetclinicwebproject.web.api.models.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorRegisterMApiControllerModel {
    private String username;
    private String password;
    private String name;
    private String specialization;
    private String description;

}
