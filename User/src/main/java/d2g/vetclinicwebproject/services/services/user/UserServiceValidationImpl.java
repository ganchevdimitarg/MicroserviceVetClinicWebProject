package d2g.vetclinicwebproject.services.services.user;

import d2g.vetclinicwebproject.services.models.UserServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static d2g.vetclinicwebproject.config.Constant.PHONE_NUMBER_VALIDATE;

@Service
public class UserServiceValidationImpl implements UserServiceValidation {

    @Override
    public boolean isValidUserRegister(UserServiceModel model) {
        return isUsernameValid(model.getUsername()) && isPasswordValid(model.getPassword());
    }

    @Override
    public boolean isValidUpdateInfo(UserServiceModel model) {
        return isNameLengthValid(model.getName()) &&
                isPhoneNumberValid(model.getPhoneNumber());
    }

    private boolean isUsernameValid(String username) {
        return !username.isEmpty() && isNameLengthValid(username);
    }

    private boolean isPasswordValid(String password) {
        return !password.isEmpty();
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return isMatches(phoneNumber, PHONE_NUMBER_VALIDATE);
    }


    private boolean isNameLengthValid(String name) {
        return name.length() >= 3 && name.length() <= 20;
    }

    private boolean isMatches(String model, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(model);
        return matcher.matches();
    }
}
