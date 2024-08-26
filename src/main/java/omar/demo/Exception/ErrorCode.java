package omar.demo.Exception;

import lombok.Getter;

public enum ErrorCode {
    ID_IS_REQUIRED(1),
    NAME_IS_REQUIRED(2),
    ID_NOT_FOUND(3),
    Author_NOT_FOUND(4),;

    @Getter
    private final int id;

    ErrorCode(final int id) {
        this.id = id;
    }
}