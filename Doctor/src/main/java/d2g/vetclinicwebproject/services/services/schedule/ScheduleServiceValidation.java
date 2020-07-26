package d2g.vetclinicwebproject.services.services.schedule;

import d2g.vetclinicwebproject.services.models.ScheduleServiceModel;

public interface ScheduleServiceValidation {
    boolean isValidSchedule(ScheduleServiceModel model);
}
