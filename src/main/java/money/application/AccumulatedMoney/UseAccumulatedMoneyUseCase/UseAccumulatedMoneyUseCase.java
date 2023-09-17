package money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase.vo.UseAccumulatedMoneyUseCaseRequestBody;
import money.core.exceptions.CommonException;
import money.domain.entity.AccumulatedMoneyHistory;
import money.domain.entity.User;
import money.domain.enums.AccumulateStatus;
import money.domain.enums.UserStatus;
import money.infra.UserRepository;
import money.interfaces.IRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static money.core.error.enums.BadRequestCode.USER_POINT_IS_MINUS;
import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UseAccumulatedMoneyUseCase {
    private UserRepository userRepository;
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public void execute(IRequest<UseAccumulatedMoneyUseCaseRequestBody> iRequest) {
        UseAccumulatedMoneyUseCaseRequestBody conditions = iRequest.getConditions();

        User user = userRepository.findUserByUuidAndStatus(UUID.fromString(conditions.getUuid()), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        if ((Integer.parseInt(user.getPoint().toString()) - conditions.getPoint()) < 0)
            throw CommonException.init(USER_POINT_IS_MINUS);

        AccumulatedMoneyHistory accumulatedMoneyHistory = new AccumulatedMoneyHistory();
        accumulatedMoneyHistory.setOnUser(user);
        accumulatedMoneyHistory.setPrice(conditions.getPoint());
        accumulatedMoneyHistory.setStatus(AccumulateStatus.USED.name());
        entityManager.persist(accumulatedMoneyHistory);
    }
}
