package money.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    public Integer status = HttpStatus.OK.value();
    public String message = "success";
}
