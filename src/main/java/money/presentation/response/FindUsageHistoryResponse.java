package money.presentation.response;


import lombok.*;
import money.core.BaseResponse;
import money.core.PageResponse;
import money.domain.AccumulatedPointHistoryDomain;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class FindUsageHistoryResponse extends BaseResponse {
    private List<AccumulatedPointHistoryDomain> accumulatedPointHistoryDomainList;
    private PageResponse pageResponse;

    public static FindUsageHistoryResponse init(
            List<AccumulatedPointHistoryDomain> accumulatedPointHistoryDomainList,
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
