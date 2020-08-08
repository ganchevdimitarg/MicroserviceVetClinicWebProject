package d2g.vetclinicwebproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "medicines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine extends BaseEntity implements Serializable {
    @Column(name = "name",nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String name;
    @Column(name = "description", nullable = false)
    @NotNull
    @NotEmpty
    private String description;
    @Column(name = "photo")
    private String imageUrl;
}
