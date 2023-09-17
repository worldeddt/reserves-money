package money.core.exceptions;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import money.core.BaseCode;


@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CommonException extends RuntimeException {
    private BaseCode baseCode;

    public static CommonException init(BaseCode baseCode) {
        return CommonException.builder().baseCode(baseCode).build();
    }
}
