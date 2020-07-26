package d2g.vetclinicwebproject.services.services.doctor;

import d2g.vetclinicwebproject.data.models.Doctor;
import d2g.vetclinicwebproject.errors.DoctorNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import d2g.vetclinicwebproject.data.repositories.DoctorRepository;
import d2g.vetclinicwebproject.services.models.DoctorServiceModel;
import d2g.vetclinicwebproject.services.services.doctor.DoctorService;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private static final String NO_SUCH_DOCTOR = "No such doctor";

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public Doctor findById(String id) {
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException(NO_SUCH_DOCTOR));
    }

    @Override
    public DoctorServiceModel findByUsername(String username) {
        return modelMapper.map(doctorRepository.findByUsername(username), DoctorServiceModel.class);
    }
}
