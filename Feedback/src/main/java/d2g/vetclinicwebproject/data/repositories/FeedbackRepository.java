package d2g.vetclinicwebproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import d2g.vetclinicwebproject.data.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}
