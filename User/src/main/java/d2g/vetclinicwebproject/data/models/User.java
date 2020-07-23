package d2g.vetclinicwebproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static d2g.vetclinicwebproject.config.Constant.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String username;
    @Column(nullable = false, unique = true)
    @NotEmpty
//    @Pattern(regexp = PASSWORD_VALIDATE)
    private String password;
    @Column(columnDefinition = "")
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String name;
    @Column(unique = true, columnDefinition = "")
    @Email
    @Pattern(regexp = EMAIL_VALIDATE)
    private String email;
    @Column(columnDefinition = "")
    private String address;
    @Column(name = "phone_number", columnDefinition = "")
    @Pattern(regexp = PHONE_NUMBER_VALIDATE)
    private String phoneNumber;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Animal> animals;
}
