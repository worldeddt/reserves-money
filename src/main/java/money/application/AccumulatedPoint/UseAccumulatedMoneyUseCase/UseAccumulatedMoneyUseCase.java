package money.application.AccumulatedPoint.UseAccumulatedMoneyUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import money.application.AccumulatedPoint.UseAccumulatedMoneyUseCase.vo.UseAccumulatedMoneyUseCaseRequestBody;
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
public class UseAccumulatedMoneyUseCase {
    private UserRepository userRepository;
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public void execute(IRequest<UseAccumulatedMoneyUseCaseRequestBody> iRequest) {
        UseAccumulatedMoneyUseCaseRequestBody conditions = iRequest.getConditions();

        User user = userRepository.findUserByUuidAndStatus(conditions.getUuid(), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        AccumulatedPointHistory accumulatedPointHistory = new AccumulatedPointHistory();
        accumulatedPointHistory.setOnUser(user);
        accumulatedPointHistory.setPrice(conditions.getPoint());
        accumulatedPointHistory.setStatus(AccumulateStatus.USED.name());
        entityManager.persist(accumulatedPointHistory);
    }
}
