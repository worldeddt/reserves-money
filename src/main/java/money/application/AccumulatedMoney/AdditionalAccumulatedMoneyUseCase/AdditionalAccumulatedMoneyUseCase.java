package money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.vo.AdditionalAccumulatedMoneyUseCaseRequestBody;
import money.core.exceptions.CommonException;
import money.domain.entity.AccumulatedMoneyHistory;
import money.domain.entity.User;
import money.domain.enums.AccumulateStatus;
import money.domain.enums.UserStatus;
import money.infra.AccumulatedMoneyRepository;
import money.infra.UserRepository;
import money.interfaces.IRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;


@Service
@AllArgsConstructor
public class AdditionalAccumulatedMoneyUseCase {
    private UserRepository userRepository;
    private AccumulatedMoneyRepository accumulatedMoneyRepository;

    @Transactional(rollbackOn = Exception.class)
    public synchronized void execute(IRequest<AdditionalAccumulatedMoneyUseCaseRequestBody> iRequest) {

        AdditionalAccumulatedMoneyUseCaseRequestBody conditions = iRequest.getConditions();

        if (conditions.getPoint() == 0)

        User user = userRepository.findUserByUuidAndStatus(UUID.fromString(conditions.getUuid()), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        AccumulatedMoneyHistory accumulatedMoneyHistory = new AccumulatedMoneyHistory();
        accumulatedMoneyHistory.setOnUser(user);
        accumulatedMoneyHistory.setPrice(conditions.getPoint());
        accumulatedMoneyHistory.setStatus(AccumulateStatus.ACCUMUL.name());
        accumulatedMoneyHistory.setPurpose(conditions.getPurpose());

        user.setPoint(user.getPoint()+conditions.getPoint());

        accumulatedMoneyRepository.saveAndFlush(accumulatedMoneyHistory);
    }
}
