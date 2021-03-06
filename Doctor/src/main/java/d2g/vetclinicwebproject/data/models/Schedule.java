package d2g.vetclinicwebproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
public class Schedule extends BaseEntity implements Serializable {
    @Column(name = "data_of_review", nullable = false)
    @NotNull
    @NotEmpty
    private String dateReview;
    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private Doctor doctor;
    @Column(name = "animal_name")
    private String animalName;
    @Column(name = "animal_id")
    private String animalId;
    @Column(name = "finished")
    private boolean isFinished;

    public Schedule() {
        this.isFinished = false;
    }
}
