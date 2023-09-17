package money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import money.domain.AccumulatedPointHistoryDomain;
import money.domain.entity.AccumulatedPointHistory;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindAccumulatedPointHistoryUseCaseResponseBody {
    private List<AccumulatedPointHistoryDomain> accumulatedPointHistoryDomains;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;

    public static FindAccumulatedPointHistoryUseCaseResponseBody init(
            List<AccumulatedPointHistoryDomain> accumulatedPointHistories,
            Integer pageNo,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean last
    ) {
        return FindAccumulatedPointHistoryUseCaseResponseBody.builder()
                .accumulatedPointHistoryDomains(accumulatedPointHistories)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .last(last)
                .build();
    }
}
