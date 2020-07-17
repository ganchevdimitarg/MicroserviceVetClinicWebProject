package d2g.vetclinicwebproject.services.services.animal.validation;


import d2g.vetclinicwebproject.services.models.AnimalServiceModel;

public interface AnimalValidationService {
    boolean isValid(AnimalServiceModel animal);
}
