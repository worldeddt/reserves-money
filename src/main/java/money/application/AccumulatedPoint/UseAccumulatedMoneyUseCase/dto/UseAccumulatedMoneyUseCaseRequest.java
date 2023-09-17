package money.application.AccumulatedPoint.UseAccumulatedMoneyUseCase.dto;


import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedPoint.UseAccumulatedMoneyUseCase.vo.UseAccumulatedMoneyUseCaseRequestBody;
import money.interfaces.IRequest;

@Builder(access = AccessLevel.PRIVATE)
public class UseAccumulatedMoneyUseCaseRequest implements IRequest<UseAccumulatedMoneyUseCaseRequestBody> {
    private Integer point;
    private String uuid;

    public static UseAccumulatedMoneyUseCaseRequest init(
            Integer point, String uuid
    ) {
        return UseAccumulatedMoneyUseCaseRequest.builder()
                .point(point)
                .uuid(uuid)
                .build();
    }

    @Override
    public UseAccumulatedMoneyUseCaseRequestBody getConditions() {
        return UseAccumulatedMoneyUseCaseRequestBody.init(
                this.point, this.uuid
        );
    }
}
