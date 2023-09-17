package money.presentation.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpUserRequest {
    @NotBlank(message = "아이디가 누락되었습니다.")
    @Size(min = 5, message = "아이디는 최소 5자리 이상입니다.")
    private String id;

    @NotBlank(message = "비밀번호가 누락되었습니다.")
    @Pattern(regexp = "^(?=.*[a-z].*[a-z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$",
            message = "영문소문자(최소 두개 이상), 숫자, 특수기호(!@#$%^*+=-) 조합 8자리 이상입니다.")
    private String password;
}
