package money.application.user.SignUpUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpUseCaseRequestBody {
    private String id;
    private String password;

    public static SignUpUseCaseRequestBody init(
            String id, String password
    ) {
        return SignUpUseCaseRequestBody.builder()
                .id(id)
                .password(password)
                .build();
    }

}
