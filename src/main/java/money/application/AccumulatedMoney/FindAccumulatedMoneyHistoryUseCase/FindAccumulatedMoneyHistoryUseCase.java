package money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.dto.FindAccumulatedMoneyHistoryUseCaseResponse;
import money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.vo.FindAccumulatedMoneyHistoryUseCaseRequestBody;
import money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.vo.FindAccumulatedMoneyHistoryUseCaseResponseBody;
import money.core.exceptions.CommonException;
import money.domain.AccumulatedMoneyHistoryDomain;
import money.domain.entity.AccumulatedMoneyHistory;
import money.infra.AccumulatedMoneyRepository;
import money.interfaces.IRequest;
import money.interfaces.IResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static money.core.error.enums.NotFoundCode.RESERVESE_MONEY_LIST_NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class FindAccumulatedMoneyHistoryUseCase {
    private AccumulatedMoneyRepository accumulatedMoneyRepository;

    public IResponse<FindAccumulatedMoneyHistoryUseCaseResponseBody> execute(
            IRequest<FindAccumulatedMoneyHistoryUseCaseRequestBody> iRequest
    ) {
        FindAccumulatedMoneyHistoryUseCaseRequestBody conditions = iRequest.getConditions();

        Page<AccumulatedMoneyHistory> accumulatedMoneyHistoryPage = accumulatedMoneyRepository.
                findAllByUserUuidOrderByIndexDesc(UUID.fromString(conditions.getUuid()), conditions.getPageable());

        List<AccumulatedMoneyHistory> accumulatedMoneyHistories = accumulatedMoneyHistoryPage.getContent();

        if (accumulatedMoneyHistories.size() == 0) throw CommonException.init(RESERVESE_MONEY_LIST_NOT_FOUND);

        List<AccumulatedMoneyHistoryDomain> accumulatedMoneyHistoryDomains = new ArrayList<>();

        accumulatedMoneyHistories.forEach(row -> {
            accumulatedMoneyHistoryDomains.add(AccumulatedMoneyHistoryDomain.init(row));
        });

        return
                FindAccumulatedMoneyHistoryUseCaseResponse.init(
                        accumulatedMoneyHistoryDomains,
                        conditions.getPageable().getPageNumber() + 1,
                        conditions.getPageable().getPageSize(),
                        accumulatedMoneyHistoryPage.getTotalElements(),
                        accumulatedMoneyHistoryPage.getTotalPages(),
                        accumulatedMoneyHistoryPage.isLast()
                );
    }
}
