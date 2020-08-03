package d2g.vetclinicwebproject.data.repository;

import d2g.vetclinicwebproject.data.model.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity, String> {
    Optional<StatsEntity> findByUsername(String username);
}
