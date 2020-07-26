package d2g.vetclinicwebproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static d2g.vetclinicwebproject.config.Constant.INVALID_TEXT_LENGTH_MASSAGE;
import static d2g.vetclinicwebproject.config.Constant.PASSWORD_VALIDATE;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
public class Doctor extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    @NotEmpty
    private String username;
    @Column(unique = true)
    @Pattern(regexp = PASSWORD_VALIDATE)
//    @NotEmpty
    private String password;
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    @NotEmpty
    private String name;
    @Column(nullable = false)
    @Size(min = 3, max = 6)
    @NotEmpty
    private String specialization;
    @Column(nullable = false)
    @Size(min = 3, max = 20)
    @NotEmpty
    private String description;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    public Doctor() {
        schedules = new ArrayList<>();
    }
}
