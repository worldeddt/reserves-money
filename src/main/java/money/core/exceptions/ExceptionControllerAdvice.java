package money.core.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import money.core.error.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e, HttpServletRequest httpServletRequest) {
        log.info("common : ");
        logErrorMessage(e, httpServletRequest.getRequestURI());
        return ResponseEntity.internalServerError().body(ErrorResponse.init(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentValidException(MethodArgumentNotValidException e,
                                                                      HttpServletRequest httpServletRequest) {
        e.getBindingResult().getFieldErrors().forEach(this::logNotValidErrorMessage);
        FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
        return ResponseEntity.badRequest().body(ErrorResponse.init(fieldError));
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Object> commonException(CommonException e, HttpServletRequest httpServletRequest) {
        logErrorMessage(e, httpServletRequest.getRequestURI());
        return ResponseEntity.status(e.getBaseCode().getHttpStatus())
                .body(ErrorResponse.init(e.getBaseCode()));
    }

    private void logNotValidErrorMessage(FieldError fieldError) {
        log.error("Field: {}", fieldError.getField());
        log.error("DefaultMessage: {}", fieldError.getDefaultMessage());
        log.error("RejectedValue: {}", fieldError.getRejectedValue());
        log.error("Code: {}", fieldError.getCode());
    }

    private void logErrorMessage(Exception e, String url) {
        log.error("Request Url: {}", url);
        log.error("e test",e.getMessage());
        log.error("Error :", e.toString());
        log.error("StackTrace: ", e);
    }
}
