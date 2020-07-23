package d2g.vetclinicwebproject.services.services.user;

import d2g.vetclinicwebproject.services.models.UserServiceModel;
import d2g.vetclinicwebproject.services.services.TestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceValidationImplTest extends TestBase {

    @Autowired
    UserServiceValidation validation;

    @Test
    void isValidUserRegister_whenUserParasIsValid_shouldReturnTrue() {
        UserServiceModel user = new UserServiceModel("1", "Ivan", "ivan@abv.bg", "Varna, Republica", "0888888888");
        user.setUsername("ivan");
        user.setPassword("111111");

        assertTrue(validation.isValidUserRegister(user));
    }

    @Test
    void isValidUserRegister_whenUsernameIsLessThanMinSize_shouldReturnFalse() {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("iv");
        user.setPassword("111111");

        assertFalse(validation.isValidUserRegister(user));
    }

    @Test
    void isValidUserRegister_whenUsernameIsMoreThanMaxSize_shouldReturnFalse() {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("ivdasdasdasdsadadasdsa");
        user.setPassword("111111");

        assertFalse(validation.isValidUserRegister(user));
    }

    @Test
    void isValidUserRegister_whenUsernameLack_shouldReturnFalse() {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("");
        user.setPassword("111111");

        assertFalse(validation.isValidUserRegister(user));
    }

    @Test
    void isValidUserRegister_whenPasswordLack_shouldReturnFalse() {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("ivan");
        user.setPassword("");

        assertFalse(validation.isValidUserRegister(user));
    }

    @Test
    void isValidUpdateInf_whenIsValid_shouldReturnTrue() {
        UserServiceModel user = new UserServiceModel("1", "Ivan", "ivan@abv.bg", "Varna, Republica", "0888888888");

        assertTrue(validation.isValidUpdateInfo(user));
    }

    @Test
    void isValidUpdateInf_whenNameIsLessThanThreeLetters_shouldReturnFalse() {
        UserServiceModel user = new UserServiceModel("1", "Iv", "ivan@abv.bg", "Varna, Republica", "0888888888");

        assertFalse(validation.isValidUpdateInfo(user));
    }

    @Test
    void isValidUpdateInf_whenNameIsMoreThanTwentyLetters_shouldReturnFalse() {
        UserServiceModel user = new UserServiceModel("1", "Ivdsadsadasddsaddasdsaddasdasdda", "ivan@abv.bg", "Varna, Republica", "0888888888");

        assertFalse(validation.isValidUpdateInfo(user));
    }

    @Test
    void isValidUpdateInf_whenEmailNotMatch_shouldReturnFalse() {
        UserServiceModel user = new UserServiceModel("1", "Ivan", "ivan@abv", "Varna, Republica", "0888888888");

        assertFalse(validation.isValidUpdateInfo(user));
    }

    @Test
    void isValidUpdateInf_whenPhoneNumberNotMatch_shouldReturnFalse() {
        UserServiceModel user = new UserServiceModel("1", "Ivan", "ivan@abv", "Varna, Republica", "08888888");

        assertFalse(validation.isValidUpdateInfo(user));
    }
}