package money.application.AccumulatedPoint.FindTotalAccumulatedMoneyUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindTotalAccumulatedMoneyUseCaseResponseBody {
    private Long point;

    public static FindTotalAccumulatedMoneyUseCaseResponseBody init(
            Long point
    ) {
        return FindTotalAccumulatedMoneyUseCaseResponseBody.builder()
                .point(point)
                .build();
    }
}
