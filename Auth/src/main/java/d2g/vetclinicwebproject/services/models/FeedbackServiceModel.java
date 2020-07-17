package d2g.vetclinicwebproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackServiceModel {
    private String name;
    private String email;
    private String subject;
    private String message;
}
