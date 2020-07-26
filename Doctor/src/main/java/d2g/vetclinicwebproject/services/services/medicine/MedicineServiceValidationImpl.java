package d2g.vetclinicwebproject.services.services.medicine;

import d2g.vetclinicwebproject.services.models.MedicineServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicineServiceValidationImpl implements MedicineServiceValidation {

    @Override
    public boolean isMedicineValid(MedicineServiceModel model) {
        return isNameEmpty(model.getName()) && isDescription(model.getDescription());
    }

    private boolean isDescription(String description) {
        return !description.isEmpty();
    }

    private boolean isNameEmpty(String name) {
        return !name.isEmpty();
    }
}
