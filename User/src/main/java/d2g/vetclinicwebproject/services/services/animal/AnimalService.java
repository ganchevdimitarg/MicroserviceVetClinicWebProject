package d2g.vetclinicwebproject.services.services.animal;

import d2g.vetclinicwebproject.services.models.AnimalServiceModel;

import java.util.List;

public interface AnimalService {
    void save(AnimalServiceModel model, String username);

    AnimalServiceModel findByName(String name);

    List<AnimalServiceModel> getCurrentUserAnimal(String username);

    void addMedicineDisease(String animalId, String medicineName, String disease);

    void deleteAnimal(String animalId);
}
