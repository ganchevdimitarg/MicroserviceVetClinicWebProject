package d2g.vetclinicwebproject.services.services.medicine;

import d2g.vetclinicwebproject.services.base.TestBase;
import d2g.vetclinicwebproject.services.models.MedicineServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MedicineServiceValidationImplTest extends TestBase {
    @Autowired
    MedicineServiceValidation validation;

    @Test
    void isMedicineValid_whenMedicineInfoIsValid_shouldReturnTrue() {
        MedicineServiceModel model = new MedicineServiceModel("migraine", "antibiotic", "");

        assertTrue(validation.isMedicineValid(model));
    }

    @Test
    void isMedicineValid_whenMedicineNameIsEmpty_shouldReturnFalse() {
        MedicineServiceModel model = new MedicineServiceModel("", "antibiotic", "");

        assertFalse(validation.isMedicineValid(model));
    }

    @Test
    void isMedicineValid_whenMedicineDescriptionIsEmpty_shouldReturnFalse() {
        MedicineServiceModel model = new MedicineServiceModel("migraine", "", "");

        assertFalse(validation.isMedicineValid(model));
    }

}