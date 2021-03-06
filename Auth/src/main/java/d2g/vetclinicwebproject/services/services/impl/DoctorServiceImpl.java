package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.services.models.*;
import d2g.vetclinicwebproject.services.services.DoctorService;
import d2g.vetclinicwebproject.web.api.models.schedule.AddTreatmentApiControllerModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private static final String MICROSERVICE_DOCTOR_URL = "http://localhost:8083/doctor/";
    private static final String MICROSERVICE_DOCTOR_URL_SCHEDULE = "http://localhost:8083/doctor/schedule";

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackDoctorHome",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000")
            },
            threadPoolKey = "doctorHomePage",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1")
            })
    @Override
    public DoctorServiceModel getDoctorHome(String username) {
        ResponseEntity<DoctorServiceModel> resp = restTemplate.exchange(MICROSERVICE_DOCTOR_URL + username,
                HttpMethod.GET, null, new ParameterizedTypeReference<DoctorServiceModel>() {
                });

        return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : new DoctorServiceModel();
    }

    @Override
    public List<ScheduleServiceModel> getSchedule() {
        return restTemplate.exchange(MICROSERVICE_DOCTOR_URL + "schedules",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ScheduleServiceModel>>() {
                }).getBody();
    }

    @Override
    public void addSchedule(AddScheduleServiceModel model) {
        AddScheduleServiceModel schedule = modelMapper.map(model, AddScheduleServiceModel.class);

        restTemplate.postForObject(MICROSERVICE_DOCTOR_URL_SCHEDULE, schedule, AddScheduleServiceModel.class);
    }

    @Override
    public void addTreatmentToAnimal(AddTreatmentServiceModel model, String scheduleId) {
        restTemplate.postForObject(MICROSERVICE_DOCTOR_URL_SCHEDULE + "/add-treatment/" + scheduleId, model, AddTreatmentApiControllerModel.class);
    }

    @Override
    public void deleteSchedule(String scheduleId, AddScheduleServiceModel model) {
        restTemplate.postForObject(MICROSERVICE_DOCTOR_URL_SCHEDULE + "/delete/" + scheduleId, model, AddScheduleServiceModel.class);
    }

    @Override
    public List<MedicineServiceModel> getMedicine() {
            return restTemplate.exchange(MICROSERVICE_DOCTOR_URL + "medicines",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<MedicineServiceModel>>() {
                    }).getBody();
    }

    @Override
    public void addMedicine(MedicineServiceModel model) {
        restTemplate.postForObject(MICROSERVICE_DOCTOR_URL + "add-medicine", model, MedicineServiceModel.class);
    }


    public DoctorServiceModel getFallbackDoctorHome(String username) {
        return new DoctorServiceModel();
    }

    public DoctorServiceModel doctorHomePage(String id, Throwable throwable) {
        System.out.printf("fallback, thread: %s input:%s, exception:%s%n",
                Thread.currentThread().getName(), id, throwable);

        return new DoctorServiceModel();
    }
}
