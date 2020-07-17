package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.services.models.FeedbackServiceModel;
import d2g.vetclinicwebproject.services.services.FeedbackService;
import d2g.vetclinicwebproject.web.api.models.feedback.FeedbackApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class FeedbackApiController {

    private final static Logger logger = LoggerFactory.getLogger(FeedbackApiController.class);

    private static final String FEEDBACK_PAGE = "/contact";

    private final FeedbackService feedbackService;
    private final ModelMapper modelMapper;

    @ModelAttribute("contactCheck")
    public FeedbackApiControllerModel contactCheck() {
        return new FeedbackApiControllerModel();
    }

    @PostMapping("/contact")
    public ResponseEntity<Void> sentFeedback(@Valid @ModelAttribute("contactCheck") FeedbackApiControllerModel feedbackModel, BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, FEEDBACK_PAGE).build();
        }

        feedbackService.sentFeedback(modelMapper.map(feedbackModel, FeedbackServiceModel.class));

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, FEEDBACK_PAGE).build();
    }
}
