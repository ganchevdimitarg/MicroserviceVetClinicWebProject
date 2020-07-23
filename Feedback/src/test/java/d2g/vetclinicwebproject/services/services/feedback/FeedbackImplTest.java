package d2g.vetclinicwebproject.services.services.feedback;

import d2g.vetclinicwebproject.data.models.Feedback;
import d2g.vetclinicwebproject.data.repositories.FeedbackRepository;
import d2g.vetclinicwebproject.services.models.FeedbackServiceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FeedbackImplTest {
    @MockBean
    FeedbackRepository feedbackRepository;

    @Autowired
    FeedbackService feedbackService;

    @BeforeEach
    private void setupTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save_whenAllParamIsCorrect_shouldSaveFeedback() {
        FeedbackServiceModel model = new FeedbackServiceModel("Ivan", "abc@abv.bg", "Test", "neshto si", "17-05-2020 16:55");

        feedbackService.save(model);

        ArgumentCaptor<Feedback> argument = ArgumentCaptor.forClass(Feedback.class);
        Mockito.verify(feedbackRepository).saveAndFlush(argument.capture());

        Feedback Feedback = argument.getValue();
        assertNotNull(Feedback);
    }
}