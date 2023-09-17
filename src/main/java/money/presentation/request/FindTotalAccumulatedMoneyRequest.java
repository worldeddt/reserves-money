package money.presentation.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindTotalAccumulatedMoneyRequest {
    @NotBlank(message = "필수값(uuid)이 누락 되었습니다.")
    private String uuid;
}
