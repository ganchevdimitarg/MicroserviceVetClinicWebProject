package d2g.vetclinicwebproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceModel extends BaseServiceModel {
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String imageUrl;
}
