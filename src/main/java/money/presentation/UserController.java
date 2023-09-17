package money.presentation;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import money.application.user.SignUpUseCase.SignUpUseCase;
import money.application.user.SignUpUseCase.dto.SignUpUseCaseRequest;
import money.application.user.SignUpUseCase.vo.SignUpUseCaseResponseBody;
import money.config.ApiErrorCodeExample;
import money.core.error.enums.BadRequestCode;
import money.core.error.enums.NotFoundCode;
import money.interfaces.IResponse;
import money.presentation.request.SignUpUserRequest;
import money.presentation.response.SignUpUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping(value = "/signUp", produces = "application/json; charset=UTF-8")
    public ResponseEntity<SignUpUserResponse> signUp(
            @RequestBody @Valid SignUpUserRequest signUpUserRequest) {

        IResponse<SignUpUseCaseResponseBody> signUpUseCaseResponseBodyIResponse =
                signUpUseCase.execute(SignUpUseCaseRequest.init(
                signUpUserRequest.getId(), signUpUserRequest.getPassword()
        ));

        SignUpUseCaseResponseBody response = signUpUseCaseResponseBodyIResponse.getResponse();

        return ResponseEntity.ok(SignUpUserResponse.init(
                response.getUuid()
        ));
    }
}
