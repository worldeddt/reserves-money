package money.application.user.SignUpUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.user.SignUpUseCase.vo.SignUpUseCaseRequestBody;
import money.interfaces.IRequest;


@Builder(access = AccessLevel.PRIVATE)
public class SignUpUseCaseRequest implements IRequest<SignUpUseCaseRequestBody> {
    private String id;
    private String password;

    public static SignUpUseCaseRequest init(
            String id, String password
    ) {
        return SignUpUseCaseRequest.builder()
                .id(id)
                .password(password)
                .build();
    }

    @Override
    public SignUpUseCaseRequestBody getConditions() {
        return SignUpUseCaseRequestBody.init(
                this.id, this.password
        )
    }
}
