package money.presentation.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UseAccumulatedMoneyRequest {

    @NotBlank(message = "필수값(uuid)이 누락 되었습니다.")
    private String uuid;

    @NotNull(message = "필수값(point)이 누락 되었습니다.")
    @Min(value = 1, message = "적립 사용액이 부족합니다.(최솟값:1) ")
    @Positive(message = "사용 처리 불가 금액입니다. (양의 정수만 사용 가능)")
    private Integer point;

    @NotBlank(message = "필수값(purpose)이 누락 되었습니다.")
    private String purpose;
}
