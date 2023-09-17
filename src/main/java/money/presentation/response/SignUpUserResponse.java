package money.presentation.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import money.core.BaseResponse;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SignUpUserResponse extends BaseResponse {
    private String uuid;

    public static SignUpUserResponse init(String uuid) {
        return SignUpUserResponse.builder()
                .uuid(uuid)
                .build();
    }
}
