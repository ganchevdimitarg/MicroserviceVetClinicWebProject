package d2g.vetclinicwebproject.config;

public abstract class Constant {
    //Minimum six characters, at least one letter and one number
    public final static String PASSWORD_VALIDATE = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
    public final static String INVALID_USERNAME_OR_PASSWORD_MASSAGE = "Invalid username or password";
    public final static String INVALID_TEXT_LENGTH_MASSAGE = "Must be between 3 and 20 characters";
}
