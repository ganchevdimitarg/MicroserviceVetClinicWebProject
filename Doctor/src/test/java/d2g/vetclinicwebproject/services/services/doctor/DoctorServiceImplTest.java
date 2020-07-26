package d2g.vetclinicwebproject.services.services.doctor;

import d2g.vetclinicwebproject.data.models.Doctor;
import d2g.vetclinicwebproject.data.repositories.DoctorRepository;
import d2g.vetclinicwebproject.errors.DoctorNotFoundException;
import d2g.vetclinicwebproject.services.base.TestBase;
import d2g.vetclinicwebproject.services.models.DoctorServiceModel;
import d2g.vetclinicwebproject.services.models.MedicineServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DoctorServiceImplTest extends TestBase {
    private static final String NO_SUCH_DOCTOR = "No such doctor";

    @MockBean
    DoctorRepository doctorRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    DoctorService services;

    @Test
    void findById_whenIdExist_shouldReturnDoctor() {
        Doctor doctor = mock(Doctor.class);
        Mockito.when(doctorRepository.findById("1")).thenReturn(java.util.Optional.ofNullable(doctor));

        assertEquals(doctor, services.findById("1"));
    }

    @Test
    void findById_whenIdNotExist_shouldThrowException() {
        Exception exception = assertThrows(DoctorNotFoundException.class, () -> {
            services.findById("1");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NO_SUCH_DOCTOR));
    }

    @Test
    void findByUsername_whenUsernameExist_shouldReturnDoctor() {
        Doctor doctor = new Doctor("ivan", "", "ivan", "", "", new ArrayList<>());
        Mockito.when(doctorRepository.findByUsername("ivan")).thenReturn(doctor);

        assertEquals("ivan", services.findByUsername("ivan").getName());
    }
}