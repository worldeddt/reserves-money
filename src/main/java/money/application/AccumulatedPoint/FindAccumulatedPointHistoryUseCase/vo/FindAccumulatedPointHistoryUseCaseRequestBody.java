package money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindAccumulatedPointHistoryUseCaseRequestBody {
    private String uuid;
    private Pageable pageable;

    public static FindAccumulatedPointHistoryUseCaseRequestBody init(
            String uuid,
            Pageable pageable
    ) {
        return FindAccumulatedPointHistoryUseCaseRequestBody.builder()
                .uuid(uuid)
                .pageable(pageable)
                .build();
    }
}
