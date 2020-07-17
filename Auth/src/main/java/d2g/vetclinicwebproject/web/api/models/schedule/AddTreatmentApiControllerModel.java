package d2g.vetclinicwebproject.web.api.models.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddTreatmentApiControllerModel {
    @NotNull
    @NotEmpty
    private String medicine;
    @NotNull
    @NotEmpty
    private String disease;
}
