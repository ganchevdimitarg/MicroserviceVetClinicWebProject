package d2g.vetclinicwebproject.services.services.animal;

import d2g.vetclinicwebproject.data.models.User;
import d2g.vetclinicwebproject.services.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalServiceImpl.class);


    private final static String INCORRECTLY_ENTERED_DATA = "Incorrectly entered data! Please try again!";
    private final static String NOT_FOUNT_ANIMAL = "No such animal";
    private final static String DUPLICATE_ANIMAL_NAME = "This name is busy";
    private final static String NOT_FOUNT_USER = "No such user";

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final AnimalValidationService validationService;
    private final ModelMapper modelMapper;

    @Override
    public void save(AnimalServiceModel model, String username) {
        if (!validationService.isValidAnimalInfo(model)) {
            throw new AnimalErrorHandlerException(INCORRECTLY_ENTERED_DATA);
        }
        if (animalRepository.findByName(model.getName()).isPresent()) {
            LOGGER.error("This name is busy");
            throw new IllegalArgumentException(DUPLICATE_ANIMAL_NAME);
        }
        Animal animal = modelMapper.map(model, Animal.class);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(NOT_FOUNT_USER));
        animal.setUser(user);

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
    public void addMedicineDisease(String animalId, String medicine, String disease) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new IllegalArgumentException(NOT_FOUNT_ANIMAL));
        animal.setMedicine(medicine);
        animal.setDisease(disease);

        animalRepository.saveAndFlush(animal);
    }

    @Override
    public void deleteAnimal(String animalId) {
        animalRepository.deleteAnimalById(animalId);
    }
}
