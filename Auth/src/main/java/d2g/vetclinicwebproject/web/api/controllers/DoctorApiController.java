package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.services.models.AddScheduleServiceModel;
import d2g.vetclinicwebproject.services.models.AddTreatmentServiceModel;
import d2g.vetclinicwebproject.services.models.MedicineServiceModel;
import d2g.vetclinicwebproject.services.services.DoctorService;
import d2g.vetclinicwebproject.web.api.models.doctor.AddMedicineApiControlModel;
import d2g.vetclinicwebproject.web.api.models.doctor.DoctorApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.doctor.MedicineApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.doctor.ScheduleApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.schedule.AddScheduleApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.schedule.AddTreatmentApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor/api")
@AllArgsConstructor
public class DoctorApiController {

    private final static Logger logger = LoggerFactory.getLogger(DoctorApiController.class);

    private static final String SCHEDULE_PAGE = "/doctor/schedule";
    private static final String MEDICINE_PAGE = "/doctor/medicine";

    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @GetMapping("/doctor-home")
    public ResponseEntity<DoctorApiControllerModel> getDoctorHome(@AuthenticationPrincipal Principal principal) {
        DoctorApiControllerModel doctor = modelMapper.map(doctorService.getDoctorHome(principal.getName()), DoctorApiControllerModel.class);

        if (doctor.getId() != null){
            return ResponseEntity.ok(doctor);
        }

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "/sign-in").build();
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleApiControllerModel>> getSchedule() {
        List<ScheduleApiControllerModel> schedules = doctorService.getSchedule()
                .stream()
                .map(s -> modelMapper.map(s, ScheduleApiControllerModel.class))
                .collect(Collectors.toList());

        if (schedules.size() != 0){
            return ResponseEntity.ok(schedules);
        }

        return ResponseEntity.notFound().build();
    }

    @ModelAttribute("addSchedule")
    public AddScheduleApiControllerModel addSchedule() {
        return new AddScheduleApiControllerModel();
    }

    @PostMapping("/schedule")
    public ResponseEntity<AddScheduleServiceModel> addSchedule(@Valid @ModelAttribute("addSchedule") AddScheduleApiControllerModel model, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, SCHEDULE_PAGE).build();
        }
        session.setAttribute("animalName", model.getAnimal());

        doctorService.addSchedule(modelMapper.map(model, AddScheduleServiceModel.class));

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, SCHEDULE_PAGE).build();
    }

    @ModelAttribute("addTreatment")
    public AddTreatmentApiControllerModel addTreatment() {
        return new AddTreatmentApiControllerModel();
    }

    @PostMapping("/add-treatment")
    public ResponseEntity<AddTreatmentServiceModel> addTreatmentToAnimal(@Valid @ModelAttribute("addTreatment") AddTreatmentApiControllerModel model, HttpSession session) {
        doctorService.addTreatmentToAnimal(modelMapper.map(model, AddTreatmentServiceModel.class), session.getAttribute("scheduleId").toString());

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, SCHEDULE_PAGE).build();
    }

    @PostMapping("/delete-finished-schedule/{scheduleId}")
    public ResponseEntity<AddScheduleServiceModel> deleteSchedule(@PathVariable String scheduleId, AddScheduleApiControllerModel model) {
        doctorService.deleteSchedule(scheduleId, modelMapper.map(model, AddScheduleServiceModel.class));

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, SCHEDULE_PAGE).build();
    }

    @GetMapping("/medicines")
    public ResponseEntity<List<MedicineApiControllerModel>> getMedicine() {
        List<MedicineApiControllerModel> medicines = doctorService.getMedicine()
                .stream()
                .map(m -> modelMapper.map(m, MedicineApiControllerModel.class))
                .collect(Collectors.toList());

        if (medicines.size() != 0){
            return new ResponseEntity<>(medicines, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ModelAttribute("addNewMedicine")
    public AddMedicineApiControlModel addNewMedicine() {
        return new AddMedicineApiControlModel();
    }

    @PostMapping("/add-medicine")
    public ResponseEntity<MedicineServiceModel> addMedicine(@Valid @ModelAttribute("addNewMedicine") AddMedicineApiControlModel model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, MEDICINE_PAGE).build();
        }

        doctorService.addMedicine(modelMapper.map(model, MedicineServiceModel.class));

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, MEDICINE_PAGE).build();
    }
}
