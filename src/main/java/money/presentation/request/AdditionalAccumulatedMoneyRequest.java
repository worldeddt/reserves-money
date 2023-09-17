package money.presentation.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalAccumulatedMoneyRequest {

    @NotBlank(message = "필수값(uuid)이 누락 되었습니다.")
    private String uuid;

    @NotNull(message = "필수값(point)이 누락 되었습니다.")
    @Min(value = 1, message = "적립금이 부족합니다.(최솟값:1) ")
    @Positive(message = "적립될 수 없는 금액입니다. (양의 정수만 사용 가능)")
    private Integer point;
}
