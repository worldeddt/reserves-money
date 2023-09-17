package money.application.AccumulatedPoint.AdditionalAccumulatedMoneyUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedPoint.AdditionalAccumulatedMoneyUseCase.vo.AdditionalAccumulatedMoneyUseCaseRequestBody;
import money.interfaces.IRequest;


@Builder(access = AccessLevel.PRIVATE)
public class AdditionalAccumulatedMoneyUseCaseRequest implements
        IRequest<AdditionalAccumulatedMoneyUseCaseRequestBody> {
    private Integer point;
    private String uuid;

    public static AdditionalAccumulatedMoneyUseCaseRequest init(
            Integer point, String uuid
    ) {
        return AdditionalAccumulatedMoneyUseCaseRequest.builder()
                .point(point)
                .uuid(uuid)
                .build();
    }

    @Override
    public AdditionalAccumulatedMoneyUseCaseRequestBody getConditions() {
        return AdditionalAccumulatedMoneyUseCaseRequestBody.init(
                this.point, this.uuid
        );
    }
}
