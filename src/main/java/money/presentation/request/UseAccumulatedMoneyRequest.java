package money.presentation.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Integer point;
}
