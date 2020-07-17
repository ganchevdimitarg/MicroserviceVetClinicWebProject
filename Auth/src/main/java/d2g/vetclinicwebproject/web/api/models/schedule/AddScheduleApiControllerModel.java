package d2g.vetclinicwebproject.web.api.models.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddScheduleApiControllerModel {
    @NotNull
    @NotEmpty
    private String dateReview;
    @NotNull
    @NotEmpty
    private String doctor;
    @NotNull
    @NotEmpty
    private String animal;
    private boolean isFinished;
}
