package money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindAccumulatedMoneyHistoryUseCaseRequestBody {
    private String uuid;
    private Pageable pageable;

    public static FindAccumulatedMoneyHistoryUseCaseRequestBody init(
            String uuid,
            Pageable pageable
    ) {
        return FindAccumulatedMoneyHistoryUseCaseRequestBody.builder()
                .uuid(uuid)
                .pageable(pageable)
                .build();
    }
}
