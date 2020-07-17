package d2g.vetclinicwebproject;

import d2g.vetclinicwebproject.data.models.Doctor;
import d2g.vetclinicwebproject.data.repositories.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DoctorInit implements CommandLineRunner {

    private final DoctorRepository doctorRepository;

    @Override
    public void run(String... args) throws Exception {

        if (doctorRepository.findByUsername("strange") == null) {
            createDoctorProfile();
        }
    }

    private void createDoctorProfile() {
        Doctor doctor = new Doctor();

        doctor.setUsername("strange");
        doctor.setName("Strange");
        doctor.setDescription("vet");
        doctor.setSpecialization("docs");

        doctorRepository.saveAndFlush(doctor);
    }
}
