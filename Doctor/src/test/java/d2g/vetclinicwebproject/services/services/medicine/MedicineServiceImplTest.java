package d2g.vetclinicwebproject.services.services.medicine;

import d2g.vetclinicwebproject.data.models.Medicine;
import d2g.vetclinicwebproject.data.repositories.MedicineRepository;
import d2g.vetclinicwebproject.services.base.TestBase;
import d2g.vetclinicwebproject.services.models.MedicineServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MedicineServiceImplTest extends TestBase {
    private static final String INVALID_DATE = "Invalid date";

    @MockBean
    MedicineRepository medicineRepository;
    @MockBean
    MedicineServiceValidation validation;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MedicineService medicineService;

    @Test
    void save_whenMedicineInfoIsValid_shouldSaveInDB() {
        MedicineServiceModel model = mock(MedicineServiceModel.class);

        Mockito.when(!validation.isMedicineValid(model)).thenReturn(true);
        medicineService.save(model);

        ArgumentCaptor<Medicine> argument = ArgumentCaptor.forClass(Medicine.class);
        Mockito.verify(medicineRepository).saveAndFlush(argument.capture());

        Medicine Medicine = argument.getValue();
        assertNotNull(Medicine);
    }

    @Test
    void save_whenMedicineInfoIsNotValid_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MedicineServiceModel model = new MedicineServiceModel("", "Gnathostomiasis is a disease from mammal feces and undercooked seafood.", "");
            medicineService.save(model);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INVALID_DATE));
    }

    @Test
    void getAll_whenUserSearchMedicines_shouldReturnAllMedicines() {
        List<Medicine> medicines = List.of(new Medicine("first", "none", ""), new Medicine("second", "none", ""));

        Mockito.when(medicineRepository.getAll()).thenReturn(medicines);

        List<MedicineServiceModel> allMedicine = medicineService.getAll();

        assertEquals(medicines.get(0).getName(), allMedicine.get(0).getName());
    }

    @Test
    void findByName_whenMedicineExist_shouldReturnTheMedicine() {
        String name = "Parasites";
        Medicine medicine = new Medicine();
        medicine.setName(name);

        Mockito.when(medicineRepository.findByName(name)).thenReturn(medicine);

        MedicineServiceModel model = medicineService.findByName("Parasites");

        assertEquals(medicine.getName(), model.getName());
    }
}