package money.presentation.response;


import lombok.*;
import money.core.BaseResponse;
import money.core.PageResponse;
import money.domain.AccumulatedMoneyHistoryDomain;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class FindUsageHistoryResponse extends BaseResponse {
    private List<AccumulatedMoneyHistoryDomain> accumulatedPointHistoryDomainList;
    private PageResponse pageResponse;

    public static FindUsageHistoryResponse init(
            List<AccumulatedMoneyHistoryDomain> accumulatedPointHistoryDomainList,
            Integer pageNo,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean last
    ) {
        return FindUsageHistoryResponse.builder()
                .accumulatedPointHistoryDomainList(accumulatedPointHistoryDomainList)
                .pageResponse(
                        PageResponse.init(
                                pageNo,
                                pageSize,
                                totalElements,
                                totalPages,
                                last
                        )
                )
                .build();
    }
}
