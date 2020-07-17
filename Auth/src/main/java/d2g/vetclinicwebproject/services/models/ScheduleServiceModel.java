package d2g.vetclinicwebproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleServiceModel {
    private String id;
    private String dateReview;
    private String doctor;
    private String animal;
    private boolean isFinished;
}
