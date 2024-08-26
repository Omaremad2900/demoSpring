package omar.demo.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class GeneralException extends Exception {
    private final ExceptionData exceptionData;

    public GeneralException(String message, ErrorCode errorCode) {
        super(message);
        exceptionData = new ExceptionData(message, errorCode.getId());
    }

    public GeneralException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        exceptionData = new ExceptionData(message, errorCode.getId());
    }
}