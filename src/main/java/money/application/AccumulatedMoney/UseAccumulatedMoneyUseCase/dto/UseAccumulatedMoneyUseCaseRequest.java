package money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase.dto;


import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase.vo.UseAccumulatedMoneyUseCaseRequestBody;
import money.interfaces.IRequest;

@Builder(access = AccessLevel.PRIVATE)
public class UseAccumulatedMoneyUseCaseRequest implements IRequest<UseAccumulatedMoneyUseCaseRequestBody> {
    private Integer point;
    private String purpose;
    private String uuid;

    public static UseAccumulatedMoneyUseCaseRequest init(
            Integer point, String purpose, String uuid
    ) {
        return UseAccumulatedMoneyUseCaseRequest.builder()
                .point(point)
                .purpose(purpose)
                .uuid(uuid)
                .build();
    }

    @Override
    public UseAccumulatedMoneyUseCaseRequestBody getConditions() {
        return UseAccumulatedMoneyUseCaseRequestBody.init(
                this.point, this.purpose, this.uuid
        );
    }
}
