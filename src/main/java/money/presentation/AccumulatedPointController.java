package money.presentation;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.FindAccumulatedPointHistoryUseCase;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.dto.FindAccumulatedPointHistoryUseCaseRequest;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo.FindAccumulatedPointHistoryUseCaseResponseBody;
import money.config.ApiErrorCodeExample;
import money.core.error.enums.BadRequestCode;
import money.core.error.enums.NotFoundCode;
import money.interfaces.IResponse;
import money.presentation.request.FindUsageHistoryRequest;
import money.presentation.response.FindUsageHistoryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping(value = "/v1/accumulated-point")
@RestController
@RequiredArgsConstructor
public class AccumulatedPointController {
    private final FindAccumulatedPointHistoryUseCase findAccumulatedPointHistoryUseCase;

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PostMapping(value = "/usage-history", produces = "application/json; charset=UTF-8")
    public ResponseEntity<FindUsageHistoryResponse> get(
            @Valid @RequestBody FindUsageHistoryRequest findUsageHistoryRequest,
            @PageableDefault(page = 1, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {

        IResponse<FindAccumulatedPointHistoryUseCaseResponseBody> response = findAccumulatedPointHistoryUseCase.execute(
                FindAccumulatedPointHistoryUseCaseRequest.init(
                        findUsageHistoryRequest.getUuid(),
                        pageable
                )
        );

        FindAccumulatedPointHistoryUseCaseResponseBody responseBody = response.getResponse();

        return ResponseEntity.ok(FindUsageHistoryResponse.init(
                responseBody.getAccumulatedPointHistoryDomains(),
                responseBody.getPageNo(),
                responseBody.getPageSize(),
                responseBody.getTotalElements(),
                responseBody.getTotalPages(),
                responseBody.getLast()
                ));
    }

}
