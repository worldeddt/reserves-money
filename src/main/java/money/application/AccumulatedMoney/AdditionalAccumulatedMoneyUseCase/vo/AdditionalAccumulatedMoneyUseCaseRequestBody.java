package money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class AdditionalAccumulatedMoneyUseCaseRequestBody {
    private Integer point;
    private String purpose;
    private String uuid;

    public static  AdditionalAccumulatedMoneyUseCaseRequestBody init(
            Integer point, String purpose, String uuid
    ) {
        return AdditionalAccumulatedMoneyUseCaseRequestBody.builder()
                .point(point)
                .purpose(purpose)
                .uuid(uuid)
                .build();
    }
}
