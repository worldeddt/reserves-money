package money.application.user.SignUpUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import money.application.user.SignUpUseCase.dto.SignUpUseCaseResponse;
import money.application.user.SignUpUseCase.vo.SignUpUseCaseRequestBody;
import money.application.user.SignUpUseCase.vo.SignUpUseCaseResponseBody;
import money.core.exceptions.CommonException;
import money.domain.entity.BaseEntity;
import money.domain.entity.User;
import money.domain.enums.UserStatus;
import money.infra.UserRepository;
import money.interfaces.IRequest;
import money.interfaces.IResponse;
import org.springframework.stereotype.Service;

import static money.core.error.enums.BadRequestCode.DUPLICATE_ID;

@Service
@AllArgsConstructor
public class SignUpUseCase {
    private UserRepository userRepository;
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public IResponse<SignUpUseCaseResponseBody> execute(IRequest<SignUpUseCaseRequestBody> iRequest) {
        SignUpUseCaseRequestBody conditions = iRequest.getConditions();

        if (userRepository.findUserByIdAndStatus(conditions.getId(), UserStatus.ACTIVE.name()).isPresent())
            throw CommonException.init(DUPLICATE_ID);

        User user = new User();
        user.setPoint(0L);
        user.setId(conditions.getId());
        user.setPassword(conditions.getPassword());
        user.setStatus(UserStatus.ACTIVE.name());

        entityManager.persist(user);

        return SignUpUseCaseResponse.init(user.getUuid().toString());
    }
}
