package d2g.vetclinicwebproject.web.views.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {
    private static final String DOCTOR_HOME_PAGE = "/home.html";
    private static final String SCHEDULE_PAGE = "doctor/schedule.html";
    private static final String ADD_TREATMENT_PAGE = "doctor/add-treatment.html";
    private static final String MEDICINE_PAGE = "doctor/medicine.html";

    @GetMapping("/doctor-home")
    public String getDoctorHome() {
        return DOCTOR_HOME_PAGE;
    }

    @GetMapping("/schedule")
    public String getSchedule() {
        return SCHEDULE_PAGE;
    }

    @GetMapping("/add-treatment/{scheduleId}")
    public String addTreatment(@PathVariable("scheduleId") String scheduleId, HttpSession session) {
        session.setAttribute("scheduleId", scheduleId);
        return ADD_TREATMENT_PAGE;
    }

    @GetMapping("/medicine")
    public String getMedicine() {
        return MEDICINE_PAGE;
    }

}
