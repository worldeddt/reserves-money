package money.presentation.response;

import lombok.*;
import money.core.BaseResponse;


@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class FindTotalAccumulatedMoneyResponse extends BaseResponse {
    private Long point;

    public static FindTotalAccumulatedMoneyResponse init(
            Long point
    ) {
        return FindTotalAccumulatedMoneyResponse.builder()
                .point(point)
                .build();
    }
}
