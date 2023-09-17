package money.application.user.SignUpUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpUseCaseResponseBody {
    private String uuid;

    public static SignUpUseCaseResponseBody init(
            String uuid
    ) {
        return SignUpUseCaseResponseBody.builder()
                .uuid(uuid)
                .build();
    }
}
