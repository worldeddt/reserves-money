package money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UseAccumulatedMoneyUseCaseRequestBody {
    private Integer point;
    private String purpose;
    private String uuid;

    public static UseAccumulatedMoneyUseCaseRequestBody init(
            Integer point, String purpose, String uuid
    ) {
        return UseAccumulatedMoneyUseCaseRequestBody.builder()
                .point(point)
                .purpose(purpose)
                .uuid(uuid)
                .build();
    }
}
