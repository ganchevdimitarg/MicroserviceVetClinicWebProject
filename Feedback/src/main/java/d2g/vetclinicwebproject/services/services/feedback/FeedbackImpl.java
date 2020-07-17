package d2g.vetclinicwebproject.services.services.feedback;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import d2g.vetclinicwebproject.data.models.Feedback;
import d2g.vetclinicwebproject.data.repositories.FeedbackRepository;
import d2g.vetclinicwebproject.services.models.FeedbackServiceModel;

@Service
@AllArgsConstructor
public class FeedbackImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(FeedbackServiceModel feedbackServiceModel) {
        feedbackRepository.saveAndFlush(modelMapper.map(feedbackServiceModel, Feedback.class));
    }
}
