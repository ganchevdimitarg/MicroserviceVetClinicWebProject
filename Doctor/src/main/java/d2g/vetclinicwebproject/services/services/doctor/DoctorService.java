package d2g.vetclinicwebproject.services.services.doctor;

import d2g.vetclinicwebproject.data.models.Doctor;
import d2g.vetclinicwebproject.services.models.DoctorServiceModel;

public interface DoctorService {
    Doctor findById(String id);

    DoctorServiceModel findByUsernameAndPassword(String username, String password);

    DoctorServiceModel findByUsername(String username);

    DoctorServiceModel findByName(String name);
}
