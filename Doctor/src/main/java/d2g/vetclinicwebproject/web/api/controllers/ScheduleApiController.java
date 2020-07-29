package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.data.models.Schedule;
import d2g.vetclinicwebproject.services.models.ScheduleServiceModel;
import d2g.vetclinicwebproject.web.api.models.treatment.AddTreatmentApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.treatment.AnimalApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.schedule.ScheduleApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import d2g.vetclinicwebproject.services.services.schedule.ScheduleService;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class ScheduleApiController {
    private static final String MICROSERVICE_USER_URL = "http://localhost:8082/user/animal/";
    private static final String NO_SUCH_USER = "No such animal";

    private final ScheduleService scheduleService;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleApiControllerModel>> getSchedule(HttpSession session) {
        List<ScheduleApiControllerModel> schedules = scheduleService.getAll()
                .stream()
                .map(s -> modelMapper.map(s, ScheduleApiControllerModel.class))
                .collect(Collectors.toList());

        if (schedules.size() != 0){
            return ResponseEntity.ok().body(schedules);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/schedule")
    public ResponseEntity<ScheduleApiControllerModel> addSchedule(@RequestBody ScheduleApiControllerModel model) {
        ScheduleServiceModel scheduleServiceModel = modelMapper.map(model, ScheduleServiceModel.class);
        AnimalApiControllerModel animalApiControllerModel;
        try {
            animalApiControllerModel = restTemplate.getForObject( MICROSERVICE_USER_URL + model.getAnimal(), AnimalApiControllerModel.class);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NO_SUCH_USER);
        }

        scheduleService.save(scheduleServiceModel, animalApiControllerModel.getId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/schedule/delete/{scheduleId}")
    public ResponseEntity<ScheduleApiControllerModel> deleteFinishedSchedule(@PathVariable String scheduleId) {
        scheduleService.deleteScheduleDelete(scheduleId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/schedule/add-treatment/{scheduleId}")
    public ResponseEntity<AddTreatmentApiControllerModel> addTreatment(@RequestBody AddTreatmentApiControllerModel model, @PathVariable String scheduleId){
        Schedule schedule = scheduleService.findById(scheduleId);
        restTemplate.postForObject(MICROSERVICE_USER_URL + "add-treatment/" + schedule.getAnimalId(), model, AddTreatmentApiControllerModel.class);

        return ResponseEntity.ok().build();
    }
}
