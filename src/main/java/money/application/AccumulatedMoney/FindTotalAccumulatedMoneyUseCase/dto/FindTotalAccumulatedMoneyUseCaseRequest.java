package money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.vo.FindTotalAccumulatedMoneyUseCaseRequestBody;
import money.interfaces.IRequest;


@Builder(access = AccessLevel.PRIVATE)
public class FindTotalAccumulatedMoneyUseCaseRequest implements IRequest<FindTotalAccumulatedMoneyUseCaseRequestBody> {
    private String uuid;

    public static FindTotalAccumulatedMoneyUseCaseRequest init(String uuid) {
        return FindTotalAccumulatedMoneyUseCaseRequest.builder()
                .uuid(uuid)
                .build();
    }

    @Override
    public FindTotalAccumulatedMoneyUseCaseRequestBody getConditions() {
        return FindTotalAccumulatedMoneyUseCaseRequestBody.init(
                this.uuid
        );
    }
}
