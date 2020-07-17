package d2g.vetclinicwebproject.services.services;


import d2g.vetclinicwebproject.services.models.AddAnimalServiceModel;
import d2g.vetclinicwebproject.web.api.models.animal.AnimalApiControllerModel;

import java.util.List;

public interface AnimalService  {
    void addAnimals(AddAnimalServiceModel animal, String username);

    List<AnimalApiControllerModel> getUserAnimals(String username);

    void deleteUser(String animalId, AddAnimalServiceModel animal);
}
