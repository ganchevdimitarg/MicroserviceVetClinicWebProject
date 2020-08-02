package d2g.vetclinicwebproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static d2g.vetclinicwebproject.config.Constant.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Size(min = 3, max = 40, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String username;
    @Column(unique = true)
//    @Pattern(regexp = PASSWORD_VALIDATE)
    private String password;
    @Column
    @Nullable
//    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String name;
    private String address;
    @Column(name = "phone_number")
    @Nullable
//    @Pattern(regexp = PHONE_NUMBER_VALIDATE, message = INVALID_PHONE_NUMBER)
    private String phoneNumber;
    @Column(name = "photos")
    private String imageUrl;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Animal> animals;
}
