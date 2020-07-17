package d2g.vetclinicwebproject.web.api.models.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AddAnimalApiControllerModel {
    private String id;
    @NotEmpty
    @Size(min = 3, max = 6)
    private String breed;
    @NotEmpty
    @Size(min = 3, max = 6)
    private String name;
    @NotNull
    @Min(value = 0)
    private double age;
    private String doctor;
    @Size(min = 3, max = 10)
    private String disease;
    private String medicine;
    private String user;
}
