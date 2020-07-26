package d2g.vetclinicwebproject.services.services.schedule;

import d2g.vetclinicwebproject.services.models.ScheduleServiceModel;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceValidationImpl implements ScheduleServiceValidation {

    @Override
    public boolean isValidSchedule(ScheduleServiceModel model) {
        return !model.getDateReview().isEmpty();
    }
}
