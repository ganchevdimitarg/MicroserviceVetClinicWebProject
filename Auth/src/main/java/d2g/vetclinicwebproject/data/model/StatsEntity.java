package d2g.vetclinicwebproject.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "stats")
@Getter
@Setter
@NoArgsConstructor
public class StatsEntity extends BaseEntity {
    private String login;
    private String logout;
    private String username;
}
