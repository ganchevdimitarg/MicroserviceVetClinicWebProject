package d2g.vetclinicwebproject.web.api.models.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackApiControllerModel {
    @NotEmpty
    @Size(min = 0, max = 30)
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 0, max = 13)
    private String subject;
    @NotEmpty
    @Size(min = 0, max = 50)
    private String message;
}
