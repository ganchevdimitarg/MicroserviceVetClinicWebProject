package d2g.vetclinicwebproject.services.services.schedule;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
//import d2g.vetclinicwebproject.data.models.Animal;
import d2g.vetclinicwebproject.data.models.Doctor;
import d2g.vetclinicwebproject.data.models.Schedule;
import d2g.vetclinicwebproject.data.repositories.DoctorRepository;
import d2g.vetclinicwebproject.data.repositories.ScheduleRepository;
import d2g.vetclinicwebproject.services.models.ScheduleServiceModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private static final String INCORRECT_DATE = "Please, try again!";

    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleServiceValidation validation;

    @Override
    public void save(ScheduleServiceModel model, String id) {
        Doctor doctor = doctorRepository.findByName(model.getDoctor());
        if (!validation.isValidSchedule(model) || doctor == null) {
            throw new IllegalArgumentException(INCORRECT_DATE);
        }

        Schedule schedule = new Schedule();
        schedule.setDateReview(model.getDateReview());
        schedule.setDoctor(doctor);
        schedule.setAnimalName(model.getAnimal());
        schedule.setAnimalId(id);

        scheduleRepository.saveAndFlush(schedule);
    }

    @Override
    public List<ScheduleServiceModel> getAll() {
        return scheduleRepository.getAll()
                .stream()
                .map(s -> {
                    ScheduleServiceModel sch = new ScheduleServiceModel();
                    sch.setId(s.getId());
                    sch.setDateReview(s.getDateReview());
                    sch.setAnimal(s.getAnimalName());
                    sch.setDoctor(doctorRepository.findByName(s.getDoctor().getName()).getName());
                    sch.setFinished(s.isFinished());
                    return sch;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteScheduleDelete(String scheduleId) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        schedule.ifPresent(s -> {
            s.setFinished(true);
            scheduleRepository.saveAndFlush(s);
        });
    }

    @Override
    public Schedule findById(String id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.get();
    }

    @Override
    public void deleteAll() {
        scheduleRepository.findAll()
                .stream()
                .filter(schedule -> !schedule.isFinished())
                .forEach(schedule -> {
                    schedule.setFinished(true);
                    scheduleRepository.saveAndFlush(schedule);
                });
    }
}
