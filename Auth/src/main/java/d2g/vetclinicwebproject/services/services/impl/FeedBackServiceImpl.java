package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.services.models.FeedbackServiceModel;
import d2g.vetclinicwebproject.services.services.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class FeedBackServiceImpl implements FeedbackService {
    private static final String FEEDBACK_URL = "http://localhost:8084/contact";

    private final RestTemplate restTemplate;

    @Override
    public void sentFeedback(FeedbackServiceModel feedbackModel) {
        restTemplate.postForObject(FEEDBACK_URL, feedbackModel, FeedbackServiceModel.class);
    }
}
