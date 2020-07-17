package d2g.vetclinicwebproject.web.api.models.treatment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalApiControllerModel {
    private String id;
    private String breed;
    private String name;
    private double age;
    private String disease;
    private String medicine;
}
