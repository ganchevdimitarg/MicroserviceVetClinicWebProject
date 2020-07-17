package d2g.vetclinicwebproject.data.repositories;

import d2g.vetclinicwebproject.data.models.Animal;
import d2g.vetclinicwebproject.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {
    Optional<Animal> findByName(String name);
    Optional<Animal> findById(String id);

    @Transactional
    @Modifying
    void deleteAnimalById(String animalId);

    @Transactional
    @Modifying
    void deleteAnimalsByUser(User user);

}
