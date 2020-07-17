package d2g.vetclinicwebproject.web.api.models.treatment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddTreatmentApiControllerModel {
    private String medicine;
    private String disease;
}
