package money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.vo.FindAccumulatedMoneyHistoryUseCaseRequestBody;
import money.interfaces.IRequest;
import org.springframework.data.domain.Pageable;


@Builder(access = AccessLevel.PRIVATE)
public class FindAccumulatedMoneyHistoryUseCaseRequest implements
        IRequest<FindAccumulatedMoneyHistoryUseCaseRequestBody> {
    private String uuid;
    private Pageable pageable;

    public static FindAccumulatedMoneyHistoryUseCaseRequest init(
            String uuid,
            Pageable pageable
    ) {
        return FindAccumulatedMoneyHistoryUseCaseRequest.builder()
                .uuid(uuid)
                .pageable(pageable)
                .build();
    }

    @Override
    public FindAccumulatedMoneyHistoryUseCaseRequestBody getConditions() {
        return FindAccumulatedMoneyHistoryUseCaseRequestBody.init(
                this.uuid, this.pageable
        );
    }
}
