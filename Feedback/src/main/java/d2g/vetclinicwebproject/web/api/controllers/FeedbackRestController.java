package d2g.vetclinicwebproject.web.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import d2g.vetclinicwebproject.config.EmailConfiguration;
import d2g.vetclinicwebproject.services.models.FeedbackServiceModel;
import d2g.vetclinicwebproject.services.services.feedback.FeedbackService;
import d2g.vetclinicwebproject.web.api.models.feedback.FeedbackControllerModel;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class FeedbackRestController {
    private final EmailConfiguration emailCfg;
    private final FeedbackService feedbackService;
    private final ModelMapper modelMapper;

    @PostMapping("/contact")
    public ResponseEntity<FeedbackControllerModel> sendFeedback(@RequestBody FeedbackControllerModel feedback, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback is not valid");
        }

        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String senderDate= formatter.format(date);

        feedback.setDate(senderDate);

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("animalParadise@gmail.com");
        mailMessage.setSubject(feedback.getSubject());
        mailMessage.setText(feedback.getMessage());
        feedbackService.save(modelMapper.map(feedback, FeedbackServiceModel.class));

        // Send mail
        mailSender.send(mailMessage);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
