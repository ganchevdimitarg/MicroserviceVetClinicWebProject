package d2g.vetclinicwebproject.web.api.models.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class AddAnimalApiControllerModel extends AnimalApiControllerModel {
    private String doctor;
    private String user;
}
