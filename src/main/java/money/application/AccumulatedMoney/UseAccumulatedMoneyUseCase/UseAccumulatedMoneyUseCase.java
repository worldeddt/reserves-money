package money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase.vo.UseAccumulatedMoneyUseCaseRequestBody;
import money.core.exceptions.CommonException;
import money.domain.entity.AccumulatedMoneyHistory;
import money.domain.entity.User;
import money.domain.enums.AccumulateStatus;
import money.domain.enums.UserStatus;
import money.infra.AccumulatedMoneyRepository;
import money.infra.UserRepository;
import money.interfaces.IRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static money.core.error.enums.BadRequestCode.USER_POINT_IS_MINUS;
import static money.core.error.enums.NotFoundCode.RESERVESE_MONEY_LIST_NOT_FOUND;
import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;

@Service
@Slf4j
@AllArgsConstructor
public class UseAccumulatedMoneyUseCase {
    private UserRepository userRepository;
    private AccumulatedMoneyRepository accumulatedMoneyRepository;

    @Transactional(rollbackOn = Exception.class)
    public synchronized void execute(IRequest<UseAccumulatedMoneyUseCaseRequestBody> iRequest) {
        UseAccumulatedMoneyUseCaseRequestBody conditions = iRequest.getConditions();

        User user = userRepository.findUserByUuidAndStatus(UUID.fromString(conditions.getUuid()), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        if ((Integer.parseInt(user.getPoint().toString()) - conditions.getPoint()) < 0)
            throw CommonException.init(USER_POINT_IS_MINUS);

        List<AccumulatedMoneyHistory> accumulatedMoneyHistories =
                accumulatedMoneyRepository.findAllByUserUuidAndStatusOrderByIndexAsc(
                        UUID.fromString(conditions.getUuid()), AccumulateStatus.ACCUMUL.name())
                        .orElseThrow(() -> CommonException.init(RESERVESE_MONEY_LIST_NOT_FOUND));

        useRecordPointOnHistoryOrderByFirstAdditional(conditions, accumulatedMoneyHistories);

        AccumulatedMoneyHistory accumulatedMoneyHistory = new AccumulatedMoneyHistory();
        accumulatedMoneyHistory.setOnUser(user);
        accumulatedMoneyHistory.setPrice(conditions.getPoint());
        accumulatedMoneyHistory.setStatus(AccumulateStatus.USED.name());
        accumulatedMoneyHistory.setPurpose(conditions.getPurpose());

        user.setPoint(user.getPoint()-conditions.getPoint());
        accumulatedMoneyRepository.saveAndFlush(accumulatedMoneyHistory);
    }

    private void useRecordPointOnHistoryOrderByFirstAdditional(
            UseAccumulatedMoneyUseCaseRequestBody conditions, List<AccumulatedMoneyHistory> accumulatedMoneyHistories) {
        Integer needMinusPointAmount = 0;

        for (AccumulatedMoneyHistory row : accumulatedMoneyHistories) {
            if (row.getAmount() == 0) continue;

            if (needMinusPointAmount != 0) {
                if ((row.getAmount() - needMinusPointAmount) < 0) {
                    needMinusPointAmount = needMinusPointAmount - row.getAmount();
                } else {
                    row.setAmount(row.getPrice() - needMinusPointAmount);
                    needMinusPointAmount = 0;
                }
            } else {
                if ((row.getAmount() - conditions.getPoint()) < 0) {
                    needMinusPointAmount = conditions.getPoint() - row.getAmount();
                    row.setAmount(0);
                } else {
                    row.setAmount(row.getAmount() - conditions.getPoint());
                }
            }

            if (needMinusPointAmount == 0) break;
        }
    }
}
