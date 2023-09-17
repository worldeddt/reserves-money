package money.presentation.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import money.core.PageRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindUsageHistoryRequest extends PageRequest {
    private String uuid;
}
