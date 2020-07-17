package d2g.vetclinicwebproject.services.services.animal;

import d2g.vetclinicwebproject.data.models.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import d2g.vetclinicwebproject.data.models.Animal;
import d2g.vetclinicwebproject.data.repositories.AnimalRepository;
import d2g.vetclinicwebproject.data.repositories.UserRepository;
import d2g.vetclinicwebproject.errors.AnimalErrorHandlerException;
import d2g.vetclinicwebproject.errors.UserNotFoundException;
import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.services.services.animal.validation.AnimalValidationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final static String INCORRECTLY_ENTERED_DATA = "Incorrectly entered data! Please try again! OR Name is not available!";
    private final static String NOT_FOUNT_ANIMAL = "No such animal";
    private final static String NOT_FOUNT_USER = "No such user";

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final AnimalValidationService validationService;
    private final ModelMapper modelMapper;

    @Override
    public void save(AnimalServiceModel model, String username) {
        if (!validationService.isValid(model)) {
            throw new AnimalErrorHandlerException(INCORRECTLY_ENTERED_DATA);
        }
        Animal animal = modelMapper.map(model, Animal.class);
        animal.setUser(userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(NOT_FOUNT_USER)));

        animalRepository.saveAndFlush(animal);
    }

    @Override
    public AnimalServiceModel findByName(String name) {
        Animal animal = animalRepository.findByName(name).orElseThrow(() -> new AnimalErrorHandlerException(NOT_FOUNT_ANIMAL));

        return modelMapper.map(animal, AnimalServiceModel.class);
    }

    @Override
    public List<AnimalServiceModel> getCurrentUserAnimal(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(NOT_FOUNT_USER))
                .getAnimals()
                .stream()
                .map(a -> modelMapper.map(a, AnimalServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public void addMedicineDisease(String animalId, String medicineName, String disease) {
        Animal animal = animalRepository.findById(animalId).get();
        animal.setMedicine(medicineName);
        animal.setDisease(disease);

        animalRepository.saveAndFlush(animal);
    }

    @Override
    public void deleteAnimal(String animalId) {
        animalRepository.deleteAnimalById(animalId);
    }

    @ExceptionHandler({AnimalErrorHandlerException.class, UserNotFoundException.class})
    public ModelAndView handleException(Throwable exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
