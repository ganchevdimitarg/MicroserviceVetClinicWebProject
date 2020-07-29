package d2g.vetclinicwebproject.services.services.schedule;

import d2g.vetclinicwebproject.data.models.Doctor;
import d2g.vetclinicwebproject.data.models.Schedule;
import d2g.vetclinicwebproject.data.repositories.DoctorRepository;
import d2g.vetclinicwebproject.data.repositories.ScheduleRepository;
import d2g.vetclinicwebproject.services.base.TestBase;
import d2g.vetclinicwebproject.services.models.MedicineServiceModel;
import d2g.vetclinicwebproject.services.models.ScheduleServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ScheduleServiceImplTest extends TestBase {

    private static final String INCORRECT_DATE = "Please, try again!";

    @MockBean
    DoctorRepository doctorRepository;
    @MockBean
    ScheduleRepository scheduleRepository;
    @MockBean
    ScheduleServiceValidation validation;

    @Autowired
    ScheduleService service;

    @Test
    void save_whenScheduleInfoIsValid_shouldSaveInDB() {
        ScheduleServiceModel schedule = mock(ScheduleServiceModel.class);
        Mockito.when(!validation.isValidSchedule(schedule)).thenReturn(true);
        service.save(schedule, "1");

        ArgumentCaptor<Schedule> argument = ArgumentCaptor.forClass(Schedule.class);
        Mockito.verify(scheduleRepository).saveAndFlush(argument.capture());

        Schedule Schedule = argument.getValue();
        assertNotNull(Schedule);
    }

    @Test
    void save_whenScheduleInfoIsNotValid_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ScheduleServiceModel schedule = new ScheduleServiceModel("1", "16", "Ivan", "Max", true);
            Mockito.when(validation.isValidSchedule(schedule)).thenReturn(false);
            service.save(schedule, "1");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INCORRECT_DATE));

    }

    @Test
    void getAll() {
        Doctor doctor = new Doctor();
        List<Schedule> schedules = List.of(new Schedule("1", doctor, "Ivan", "Max", true),
                new Schedule("2", doctor,"Rox" , "Rok", true));
        doctor.setName("Ivan");
        doctor.setSchedules(schedules);
        Mockito.when(doctorRepository.findByName("Ivan")).thenReturn(doctor);
        Mockito.when(scheduleRepository.getAll()).thenReturn(schedules);

        List<ScheduleServiceModel> schedulesDB = service.getAll();

        assertEquals(2, schedulesDB.size());
    }


    @Test
    void deleteScheduleDelete() {
        Schedule schedule = new Schedule();
        schedule.setId("1");

        Mockito.when(scheduleRepository.findById("1")).thenReturn(Optional.of(schedule));
        service.deleteScheduleDelete("1");

        assertTrue(schedule.isFinished());

    }

    @Test
    void findById_whenScheduleExists_shouldReturnTheSchedule() {
        Schedule schedule = new Schedule();
        schedule.setId("1");
        Mockito.when(scheduleRepository.findById("1")).thenReturn(java.util.Optional.of(schedule));

        Schedule scheduleDB = service.findById("1");

        assertEquals("1", scheduleDB.getId());
    }

}