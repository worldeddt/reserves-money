package money.core.error.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import money.core.BaseCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;



@Getter
@Builder(access = AccessLevel.PRIVATE)
@Slf4j
public class ErrorResponse {
    private Integer status;
    private String message;

    public static ErrorResponse init(BaseCode baseCode) {
        return ErrorResponse.builder()
                .status(baseCode.getHttpStatus().value())
                .message(baseCode.getMessage()).build();
    }

    public static ErrorResponse init(Exception e) {
        return ErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage()).build();
    }

    public static ErrorResponse init(FieldError fieldError) {
        log.info("fieldError :"+fieldError.getDefaultMessage());
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(fieldError.getDefaultMessage())
                .build();
    }
}
