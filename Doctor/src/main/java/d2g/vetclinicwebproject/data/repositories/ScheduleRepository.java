package d2g.vetclinicwebproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import d2g.vetclinicwebproject.data.models.Schedule;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    @Query("select s from Schedule s")
    List<Schedule> getAll();

}
