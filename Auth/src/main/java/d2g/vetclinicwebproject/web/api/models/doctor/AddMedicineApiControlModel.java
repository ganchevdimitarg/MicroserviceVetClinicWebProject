package d2g.vetclinicwebproject.web.api.models.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AddMedicineApiControlModel {
    @NotNull
    @Size(min = 3, max = 10)
    private String name;
    @NotNull
    @Size(min = 5, max = 40)
    private String description;
    private MultipartFile image;
}
