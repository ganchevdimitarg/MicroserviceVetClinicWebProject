package d2g.vetclinicwebproject.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "feedback")
@Setter
@Getter
@NoArgsConstructor
public class Feedback extends BaseEntity {
    @Column(nullable = false)
    @NotEmpty
    private String name;
    @Column(nullable = false)
    @NotEmpty
    @Email
    private String email;
    @Column(nullable = false)
    @NotEmpty
    private String subject;
    @Column(nullable = false)
    @NotEmpty
    private String message;
    @Column(nullable = false)
    @NotEmpty
    private String date;
}
