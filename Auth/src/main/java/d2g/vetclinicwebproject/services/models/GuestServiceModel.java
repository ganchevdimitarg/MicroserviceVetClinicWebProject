package d2g.vetclinicwebproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestServiceModel {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
