package d2g.vetclinicwebproject.services.services.user;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import d2g.vetclinicwebproject.data.models.User;
import d2g.vetclinicwebproject.data.repositories.AnimalRepository;
import d2g.vetclinicwebproject.data.repositories.UserRepository;
import d2g.vetclinicwebproject.errors.UserNotFoundException;
import d2g.vetclinicwebproject.services.models.UserServiceModel;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String NO_SUCH_USER = "Not such User";
    private static final String INVALID_PARAMS = "Username or password is not valid";
    private static final String DATA_IS_NOT_VALID = "The data is not valid. Try again";

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final UserServiceValidation validation;
    private final ModelMapper modelMapper;

    @Override
    public void registerUser(UserServiceModel model) throws IllegalAccessException {
        if(!validation.isValidUserRegister(model)){
            throw new IllegalAccessException(INVALID_PARAMS);
        }
        userRepository.saveAndFlush(modelMapper.map(model, User.class));
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return modelMapper.map(getUserByUsername(username), UserServiceModel.class);
    }

    @Override
    public void updateUser(UserServiceModel model) {
        if (!validation.isValidUpdateInfo(model)){
            throw new IllegalArgumentException(DATA_IS_NOT_VALID);
        }
        User user = modelMapper.map(model, User.class);
        userRepository.update(user.getName(), user.getEmail(), user.getAddress(), user.getPhoneNumber(), user.getUsername());
    }

    @Override
    public void deleteUser(String username) {
        User user = getUserByUsername(username);
        user.getAnimals().removeAll(user.getAnimals());
        animalRepository.deleteAnimalsByUser(user);
        userRepository.deleteByUsername(username);
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(NO_SUCH_USER));
    }
}
