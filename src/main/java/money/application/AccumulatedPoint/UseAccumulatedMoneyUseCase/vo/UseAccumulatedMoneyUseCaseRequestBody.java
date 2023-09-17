package money.application.AccumulatedPoint.UseAccumulatedMoneyUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UseAccumulatedMoneyUseCaseRequestBody {
    private Integer point;
    private String uuid;

    public static UseAccumulatedMoneyUseCaseRequestBody init(
            Integer point, String uuid
    ) {
        return UseAccumulatedMoneyUseCaseRequestBody.builder()
                .point(point)
                .uuid(uuid)
                .build();
    }
}
