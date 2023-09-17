package money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.vo.AdditionalAccumulatedMoneyUseCaseRequestBody;
import money.interfaces.IRequest;


@Builder(access = AccessLevel.PRIVATE)
public class AdditionalAccumulatedMoneyUseCaseRequest implements
        IRequest<AdditionalAccumulatedMoneyUseCaseRequestBody> {
    private Integer point;
    private String purpose;
    private String uuid;

    public static AdditionalAccumulatedMoneyUseCaseRequest init(
            Integer point, String purpose, String uuid
    ) {
        return AdditionalAccumulatedMoneyUseCaseRequest.builder()
                .point(point)
                .purpose(purpose)
                .uuid(uuid)
                .build();
    }

    @Override
    public AdditionalAccumulatedMoneyUseCaseRequestBody getConditions() {
        return AdditionalAccumulatedMoneyUseCaseRequestBody.init(
                this.point, this.purpose, this.uuid
        );
    }
}
