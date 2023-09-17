package money.core;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class PageRequest {

    @NotNull(message = "필수값(pageNo)이 누락되었습니다.")
    private Integer pageNo;
    @NotNull(message = "필수값(pageSize)이 누락되었습니다.")
    private Integer pageSize;
}
