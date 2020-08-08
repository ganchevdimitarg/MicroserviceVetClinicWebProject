package d2g.vetclinicwebproject.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "stats")
@Getter
@Setter
@NoArgsConstructor
public class StatsEntity extends BaseEntity {
    @Column(name = "first_login")
    private String login;
    @Column(name = "last_logout")
    private String logout;
    @Column(name = "username")
    private String username;
}
