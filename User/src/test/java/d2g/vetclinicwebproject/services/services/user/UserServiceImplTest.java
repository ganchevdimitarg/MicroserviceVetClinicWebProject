package d2g.vetclinicwebproject.services.services.user;

import d2g.vetclinicwebproject.data.models.User;
import d2g.vetclinicwebproject.data.repositories.AnimalRepository;
import d2g.vetclinicwebproject.data.repositories.UserRepository;
import d2g.vetclinicwebproject.errors.UserNotFoundException;
import d2g.vetclinicwebproject.services.models.UserServiceModel;
import d2g.vetclinicwebproject.services.TestBase;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceImplTest extends TestBase {
    private static final String NO_SUCH_USER = "Not such User";
    private static final String INVALID_PARAMS = "Username or password is not valid";
    private static final String DATA_IS_NOT_VALID = "The data is not valid. Try again";


    @MockBean
    UserRepository userRepository;
    @MockBean
    AnimalRepository animalRepository;
    @MockBean
    UserServiceValidation validation;


    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @Test
    void registerUser_whenUsernameAndPasswordIsValid_shouldSuccessfulRegisterUser() throws IllegalAccessException {
        UserServiceModel userModel = mock(UserServiceModel.class);

        Mockito.when(!validation.isValidUserRegister(userModel)).thenReturn(true);
        userService.registerUser(userModel);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).saveAndFlush(argument.capture());

        User user = argument.getValue();
        assertNotNull(user);
    }

    @Test
    void registerUser_whenUsernameIsNotValidOrPasswordIsNotValid_shouldThrowException() {
        Exception exception = assertThrows(IllegalAccessException.class, () -> {
            UserServiceModel userModel = mock(UserServiceModel.class);
            Mockito.when(!validation.isValidUserRegister(userModel)).thenReturn(false);
            userService.registerUser(userModel);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INVALID_PARAMS));
    }

    @Test
    void findByUsername_whenUsernameIsCorrect_shouldReturnUser() {
        String username = "Dimitar";
        User userBefore = new User();
        userBefore.setUsername(username);

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(userBefore));

        UserServiceModel userAfter = userService.findByUsername(username);

        assertEquals(userBefore.getName(), userAfter.getName());
    }

    @Test
    void findByUsername_whenUsernameIsNotFound_shouldThrowException() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            String username = "Dimitar";
            User user = new User();
            user.setUsername("Ivan");

            Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
            userService.findByUsername(username);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NO_SUCH_USER));
    }

    @Test
    void updateUser_whenAllInfoIsValid_shouldUpdateUserInfo() {
        UserServiceModel userModel = new UserServiceModel("", "Ivan Ivanov", "", "", "");
        userModel.setUsername("ivan");
        userModel.setPassword("111111");

        User user = new User();
        user.setName("Ivan Ivanov");

        Mockito.when(!validation.isValidUpdateInfo(userModel)).thenReturn(true);
        userService.updateUser(userModel);
        Mockito.when(userRepository.findByUsername("ivan")).thenReturn(Optional.of(user));

        User actualUser = userRepository.findByUsername(userModel.getUsername()).get();

        assertEquals(userModel.getName(), actualUser.getName());
    }

    @Test
    void updateUser_whenSameThingInfoIsNotValid_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserServiceModel userModel = new UserServiceModel("", "", "", "", "");
            Mockito.when(!validation.isValidUpdateInfo(userModel)).thenReturn(false);
            userService.updateUser(userModel);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(DATA_IS_NOT_VALID));
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setUsername("ivan");
        user.setAnimals(new ArrayList<>());

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        userService.deleteUser(user.getUsername());
        User user1 = userRepository.findByUsername(user.getUsername()).get();

        assertNotNull(user1);
    }

}