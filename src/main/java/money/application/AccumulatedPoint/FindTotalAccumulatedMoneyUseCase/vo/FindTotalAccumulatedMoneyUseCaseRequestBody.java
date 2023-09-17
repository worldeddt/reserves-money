package money.application.AccumulatedPoint.FindTotalAccumulatedMoneyUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindTotalAccumulatedMoneyUseCaseRequestBody {
    private String uuid;

    public static FindTotalAccumulatedMoneyUseCaseRequestBody init(String uuid) {
        return FindTotalAccumulatedMoneyUseCaseRequestBody.builder()
                .uuid(uuid)
                .build();
    }
}
