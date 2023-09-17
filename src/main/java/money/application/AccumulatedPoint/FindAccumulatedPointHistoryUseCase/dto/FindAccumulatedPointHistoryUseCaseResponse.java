package money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo.FindAccumulatedPointHistoryUseCaseRequestBody;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo.FindAccumulatedPointHistoryUseCaseResponseBody;
import money.domain.AccumulatedPointHistoryDomain;
import money.interfaces.IRequest;
import money.interfaces.IResponse;

import java.util.List;


@Builder(access = AccessLevel.PRIVATE)
public class FindAccumulatedPointHistoryUseCaseResponse implements
        IResponse<FindAccumulatedPointHistoryUseCaseResponseBody> {

    private List<AccumulatedPointHistoryDomain> accumulatedPointHistoryDomains;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;


    public static FindAccumulatedPointHistoryUseCaseResponse init(
            List<AccumulatedPointHistoryDomain> accumulatedPointHistoryDomains,
            Integer pageNo,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean last

    ) {
        return FindAccumulatedPointHistoryUseCaseResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .accumulatedPointHistoryDomains(accumulatedPointHistoryDomains)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .last(last)
                .build();
    }

    @Override
    public List<FindAccumulatedPointHistoryUseCaseResponseBody> getResponses() {
        return null;
    }

    @Override
    public FindAccumulatedPointHistoryUseCaseResponseBody getResponse() {
        return FindAccumulatedPointHistoryUseCaseResponseBody.init(
                this.accumulatedPointHistoryDomains,
                this.pageNo,
                this.pageSize,
                this.totalElements,
                this.totalPages,
                this.last
        );
    }
}
