package money.application.user.SignUpUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import money.application.user.SignUpUseCase.dto.SignUpUseCaseResponse;
import money.application.user.SignUpUseCase.vo.SignUpUseCaseRequestBody;
import money.application.user.SignUpUseCase.vo.SignUpUseCaseResponseBody;
import money.domain.entity.User;
import money.interfaces.IRequest;
import money.interfaces.IResponse;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignUpUseCase {
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public IResponse<SignUpUseCaseResponseBody> execute(IRequest<SignUpUseCaseRequestBody> iRequest) {
        SignUpUseCaseRequestBody conditions = iRequest.getConditions();

        User user = new User();
        user.setMoney(0L);
        user.setId(conditions.getId());
        user.setPassword(conditions.getPassword());

        entityManager.persist(user);

        return SignUpUseCaseResponse.init(user.getUuid().toString());
    }
}
