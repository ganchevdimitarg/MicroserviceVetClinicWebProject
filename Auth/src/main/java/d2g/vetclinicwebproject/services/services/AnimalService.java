package d2g.vetclinicwebproject.services.services;


import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.web.api.models.animal.AnimalApiControllerModel;

import java.util.List;

public interface AnimalService  {
    void addAnimals(AnimalServiceModel animal, String username);

    List<AnimalServiceModel> getUserAnimals(String username);

    void deleteUser(String animalId, AnimalServiceModel animal);
}
