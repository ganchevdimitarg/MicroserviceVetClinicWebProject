package d2g.vetclinicwebproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnimalServiceModel {
    private String id;
    private String breed;
    private String name;
    private double age;
    private String user;
    private String disease;
    private String medicine;
}
