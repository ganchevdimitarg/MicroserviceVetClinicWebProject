package d2g.vetclinicwebproject.web.api.models.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleApiControllerModel {
    private String id;
    private String dateReview;
    private String doctor;
    private String animal;
    private boolean isFinished;
}
