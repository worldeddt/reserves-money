package money.application.AccumulatedPoint.AdditionalAccumulatedMoneyUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import money.application.AccumulatedPoint.AdditionalAccumulatedMoneyUseCase.vo.AdditionalAccumulatedMoneyUseCaseRequestBody;
import money.core.exceptions.CommonException;
import money.domain.entity.AccumulatedPointHistory;
import money.domain.entity.User;
import money.domain.enums.AccumulateStatus;
import money.domain.enums.UserStatus;
import money.infra.UserRepository;
import money.interfaces.IRequest;
import org.springframework.stereotype.Service;

import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;


@Service
@AllArgsConstructor
public class AdditionalAccumulatedMoneyUseCase {
    private UserRepository userRepository;
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public void execute(IRequest<AdditionalAccumulatedMoneyUseCaseRequestBody> iRequest) {

        AdditionalAccumulatedMoneyUseCaseRequestBody conditions = iRequest.getConditions();

        User user = userRepository.findUserByUuidAndStatus(conditions.getUuid(), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        AccumulatedPointHistory accumulatedPointHistory = new AccumulatedPointHistory();
        accumulatedPointHistory.setOnUser(user);
        accumulatedPointHistory.setPrice(conditions.getPoint());
        accumulatedPointHistory.setStatus(AccumulateStatus.ACCUMUL.name());
        entityManager.persist(accumulatedPointHistory);
    }
}
