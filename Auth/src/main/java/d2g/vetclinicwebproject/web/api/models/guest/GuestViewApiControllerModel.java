package d2g.vetclinicwebproject.web.api.models.guest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestViewApiControllerModel {
    private String id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
