package money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.vo.FindAccumulatedMoneyHistoryUseCaseResponseBody;
import money.domain.AccumulatedMoneyHistoryDomain;
import money.interfaces.IResponse;

import java.util.List;


@Builder(access = AccessLevel.PRIVATE)
public class FindAccumulatedMoneyHistoryUseCaseResponse implements
        IResponse<FindAccumulatedMoneyHistoryUseCaseResponseBody> {

    private List<AccumulatedMoneyHistoryDomain> accumulatedMoneyHistoryDomains;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;


    public static FindAccumulatedMoneyHistoryUseCaseResponse init(
            List<AccumulatedMoneyHistoryDomain> accumulatedMoneyHistoryDomains,
            Integer pageNo,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean last

    ) {
        return FindAccumulatedMoneyHistoryUseCaseResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .accumulatedMoneyHistoryDomains(accumulatedMoneyHistoryDomains)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .last(last)
                .build();
    }

    @Override
    public List<FindAccumulatedMoneyHistoryUseCaseResponseBody> getResponses() {
        return null;
    }

    @Override
    public FindAccumulatedMoneyHistoryUseCaseResponseBody getResponse() {
        return FindAccumulatedMoneyHistoryUseCaseResponseBody.init(
                this.accumulatedMoneyHistoryDomains,
                this.pageNo,
                this.pageSize,
                this.totalElements,
                this.totalPages,
                this.last
        );
    }
}
