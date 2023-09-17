package money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase;


import lombok.AllArgsConstructor;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.dto.FindAccumulatedPointHistoryUseCaseResponse;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo.FindAccumulatedPointHistoryUseCaseRequestBody;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo.FindAccumulatedPointHistoryUseCaseResponseBody;
import money.core.error.enums.NotFoundCode;
import money.core.exceptions.CommonException;
import money.domain.AccumulatedPointHistoryDomain;
import money.domain.entity.AccumulatedPointHistory;
import money.domain.entity.User;
import money.infra.AccumulatedPointRepository;
import money.infra.UserRepository;
import money.interfaces.IRequest;
import money.interfaces.IResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static money.core.error.enums.NotFoundCode.RESERVESE_MONEY_LIST_NOT_FOUND;

@Service
@AllArgsConstructor
public class FindAccumulatedPointHistoryUseCase {
    private AccumulatedPointRepository accumulatedPointRepository;

    public IResponse<FindAccumulatedPointHistoryUseCaseResponseBody> execute(
            IRequest<FindAccumulatedPointHistoryUseCaseRequestBody> iRequest
    ) {
        FindAccumulatedPointHistoryUseCaseRequestBody conditions = iRequest.getConditions();

        Page<AccumulatedPointHistory> accumulatedPointHistoryPage = accumulatedPointRepository.
                findAllByUserUuidOrderByIndexDesc(conditions.getUuid(), conditions.getPageable());

        List<AccumulatedPointHistory> accumulatedPointHistories = accumulatedPointHistoryPage.getContent();

        if (accumulatedPointHistories.size() == 0) throw CommonException.init(RESERVESE_MONEY_LIST_NOT_FOUND);

        List<AccumulatedPointHistoryDomain> accumulatedPointHistoryDomains = new ArrayList<>();

        accumulatedPointHistories.forEach(row -> {
            accumulatedPointHistoryDomains.add(AccumulatedPointHistoryDomain.init(row));
        });

        return
                FindAccumulatedPointHistoryUseCaseResponse.init(
                        accumulatedPointHistoryDomains,
                        conditions.getPageable().getPageNumber(),
                        conditions.getPageable().getPageSize(),
                        accumulatedPointHistoryPage.getTotalElements(),
                        accumulatedPointHistoryPage.getTotalPages(),
                        accumulatedPointHistoryPage.isLast()
                );
    }
}
