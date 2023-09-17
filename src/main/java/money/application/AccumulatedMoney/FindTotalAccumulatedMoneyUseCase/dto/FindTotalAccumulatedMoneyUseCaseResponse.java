package money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.dto;


import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.vo.FindTotalAccumulatedMoneyUseCaseResponseBody;
import money.interfaces.IResponse;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public class FindTotalAccumulatedMoneyUseCaseResponse implements IResponse<FindTotalAccumulatedMoneyUseCaseResponseBody> {
    private Long point;

    public static FindTotalAccumulatedMoneyUseCaseResponse init(
            Long point
    ) {
        return FindTotalAccumulatedMoneyUseCaseResponse.builder()
                .point(point)
                .build();
    }

    @Override
    public List<FindTotalAccumulatedMoneyUseCaseResponseBody> getResponses() {
        return null;
    }

    @Override
    public FindTotalAccumulatedMoneyUseCaseResponseBody getResponse() {
        return FindTotalAccumulatedMoneyUseCaseResponseBody.init(this.point);
    }
}
