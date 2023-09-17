package money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import money.domain.AccumulatedMoneyHistoryDomain;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindAccumulatedMoneyHistoryUseCaseResponseBody {
    private List<AccumulatedMoneyHistoryDomain> accumulatedMoneyHistoryDomains;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;

    public static FindAccumulatedMoneyHistoryUseCaseResponseBody init(
            List<AccumulatedMoneyHistoryDomain> accumulatedMoneyHistories,
            Integer pageNo,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean last
    ) {
        return FindAccumulatedMoneyHistoryUseCaseResponseBody.builder()
                .accumulatedMoneyHistoryDomains(accumulatedMoneyHistories)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .last(last)
                .build();
    }
}
