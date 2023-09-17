package money.application.user.SignUpUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.user.SignUpUseCase.vo.SignUpUseCaseResponseBody;
import money.interfaces.IResponse;

import java.util.List;


@Builder(access = AccessLevel.PRIVATE)
public class SignUpUseCaseResponse implements IResponse<SignUpUseCaseResponseBody> {
    private String uuid;

    public static SignUpUseCaseResponse init(
            String uuid
    ) {
        return SignUpUseCaseResponse.builder()
                .uuid(uuid)
                .build();
    }

    @Override
    public List<SignUpUseCaseResponseBody> getResponses() {
        return null;
    }

    @Override
    public SignUpUseCaseResponseBody getResponse() {
        return SignUpUseCaseResponseBody.init(this.uuid);
    }
}
