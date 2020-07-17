package d2g.vetclinicwebproject.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "No such animal")
public class AnimalErrorHandlerException extends RuntimeException {
    public AnimalErrorHandlerException(String message) {
        super(message);
    }
}
