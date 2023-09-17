package money.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import money.domain.entity.AccumulatedMoneyHistory;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class AccumulatedMoneyHistoryDomain {
    private Integer index;
    private String status;
    private Integer price;
    private String registerDatetime;
    private String updateDatetime;

    public static AccumulatedMoneyHistoryDomain init(
            AccumulatedMoneyHistory accumulatedPointHistory
    ) {
        return AccumulatedMoneyHistoryDomain.builder()
                .index(accumulatedPointHistory.getIndex())
                .price(accumulatedPointHistory.getPrice())
                .status(accumulatedPointHistory.getStatus())
                .registerDatetime(
                        accumulatedPointHistory.getRegisterDatetime() == null ? "0000-00-00 00:00:00"
                                : accumulatedPointHistory.getRegisterDatetime().toString()
                )
                .updateDatetime(
                        accumulatedPointHistory.getUpdateDatetime() == null ? "0000-00-00 00:00:00"
                                : accumulatedPointHistory.getUpdateDatetime().toString()
                )
                .build();
    }
}
