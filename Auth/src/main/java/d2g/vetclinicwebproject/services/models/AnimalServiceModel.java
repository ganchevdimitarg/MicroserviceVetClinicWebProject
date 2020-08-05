package d2g.vetclinicwebproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalServiceModel {
    private String id;
    private String breed;
    private String name;
    private double age;
    private String doctor;
    private String disease;
    private String medicine;
    private String user;
}
