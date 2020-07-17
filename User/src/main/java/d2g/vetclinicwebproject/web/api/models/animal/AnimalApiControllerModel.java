package d2g.vetclinicwebproject.web.api.models.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnimalApiControllerModel {
    private String id;
    private String breed;
    private String name;
    private double age;
    private String disease;
    private String medicine;
}
