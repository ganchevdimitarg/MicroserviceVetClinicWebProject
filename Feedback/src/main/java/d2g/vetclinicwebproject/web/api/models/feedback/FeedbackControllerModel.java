package d2g.vetclinicwebproject.web.api.models.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackControllerModel {
    private String name;
    private String email;
    private String subject;
    private String message;
    private String date;
}
