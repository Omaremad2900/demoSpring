package omar.demo.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Getter
public class ValidationException extends Exception {
    private final String msg;

    public ValidationException(String message) {
        super(message);
        msg = message;
    }
}
