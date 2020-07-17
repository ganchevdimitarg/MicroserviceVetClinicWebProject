package d2g.vetclinicwebproject.web.api.models.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddTreatmentControllerModel {
    private String medicine;
    private String disease;
}
