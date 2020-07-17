package d2g.vetclinicwebproject.services.services.schedule;

import d2g.vetclinicwebproject.data.models.Schedule;
import d2g.vetclinicwebproject.services.models.ScheduleServiceModel;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    void save(ScheduleServiceModel model, String id);

    List<ScheduleServiceModel> getAll();

    void deleteScheduleDelete(String scheduleId);

    Schedule findById(String id);
}
