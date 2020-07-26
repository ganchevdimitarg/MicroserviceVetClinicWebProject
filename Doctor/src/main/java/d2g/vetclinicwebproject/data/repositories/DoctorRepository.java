package d2g.vetclinicwebproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import d2g.vetclinicwebproject.data.models.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Optional<Doctor> findById(String id);

    Doctor findByUsername(String username);

    Doctor findByName(String name);
}
