package d2g.vetclinicwebproject.web.api.models.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorApiControllerModel {
    private String id;
    private String name;
    private String specialization;
    private String description;
}
