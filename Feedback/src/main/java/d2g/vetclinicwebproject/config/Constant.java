package d2g.vetclinicwebproject.config;

public abstract class Constant {
    public final static String EMAIL_VALIDATE = "^[\\w!#$%&’*+\\=?`{|}~^-]+(?:\\.[\\w!#$%&’*+\\=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    //Minimum six characters, at least one letter and one number
    public final static String PASSWORD_VALIDATE = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
    public final static String PHONE_NUMBER_VALIDATE = "(\\+)?(359|0)8[789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}";
    public final static String INVALID_USERNAME_OR_PASSWORD_MASSAGE = "Invalid username or password";
    public final static String INVALID_TEXT_LENGTH_MASSAGE = "Must be between 3 and 20 characters";
    public final static String INVALID_LOGIN_KEY_LENGTH_MASSAGE = "Must be 6 characters";
}
