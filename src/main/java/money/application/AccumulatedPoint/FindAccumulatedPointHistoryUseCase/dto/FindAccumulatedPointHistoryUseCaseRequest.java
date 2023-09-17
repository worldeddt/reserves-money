package money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo.FindAccumulatedPointHistoryUseCaseRequestBody;
import money.interfaces.IRequest;
import org.springframework.data.domain.Pageable;


@Builder(access = AccessLevel.PRIVATE)
public class FindAccumulatedPointHistoryUseCaseRequest implements
        IRequest<FindAccumulatedPointHistoryUseCaseRequestBody> {
    private String uuid;
    private Pageable pageable;

    public static FindAccumulatedPointHistoryUseCaseRequest init(
            String uuid,
            Pageable pageable
    ) {
        return FindAccumulatedPointHistoryUseCaseRequest.builder()
                .uuid(uuid)
                .pageable(pageable)
                .build();
    }

    @Override
    public FindAccumulatedPointHistoryUseCaseRequestBody getConditions() {
        return FindAccumulatedPointHistoryUseCaseRequestBody.init(
                this.uuid, this.pageable
        );
    }
}
