package d2g.vetclinicwebproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "animals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal extends BaseEntity implements Serializable {
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 6)
    private String breed;
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Size(min = 3, max = 6)
    private String name;
    @Column(nullable = false)
    @NotNull
    @Min(value = 0)
    private double age;
    @Size(min = 3, max = 10)
    private String disease;
    @Size(min = 3, max = 10)
    private String medicine;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
