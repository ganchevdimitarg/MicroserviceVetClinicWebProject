package d2g.vetclinicwebproject.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class Schedule extends BaseEntity {
    @Column(name = "data_of_review", nullable = false)
    @NotNull
    @NotEmpty
    private String dateReview;
    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private Doctor doctor;
    private String animalName;
    private String animalId;
    private boolean isFinished;

    public Schedule() {
        this.isFinished = false;
    }
}
