package money.core.error.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import money.core.BaseCode;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum NotFoundCode implements BaseCode {
    USER_NOT_FOUND("유저 정보를 불러올 수 없습니다.", NOT_FOUND),
    RESERVESE_MONEY_LIST_NOT_FOUND("적립금 리스트를 찾을 수 없습니다.", NOT_FOUND);
    private final String message;
    private final HttpStatus httpStatus;
}
