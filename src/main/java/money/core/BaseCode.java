package money.core;

import org.springframework.http.HttpStatus;

public interface BaseCode {
    String name();

    String getMessage();

    HttpStatus getHttpStatus();
}
