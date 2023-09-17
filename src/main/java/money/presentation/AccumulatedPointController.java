package money.presentation;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import money.application.AccumulatedPoint.AdditionalAccumulatedMoneyUseCase.AdditionalAccumulatedMoneyUseCase;
import money.application.AccumulatedPoint.AdditionalAccumulatedMoneyUseCase.dto.AdditionalAccumulatedMoneyUseCaseRequest;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.FindAccumulatedPointHistoryUseCase;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.dto.FindAccumulatedPointHistoryUseCaseRequest;
import money.application.AccumulatedPoint.FindAccumulatedPointHistoryUseCase.vo.FindAccumulatedPointHistoryUseCaseResponseBody;
import money.application.AccumulatedPoint.UseAccumulatedMoneyUseCase.UseAccumulatedMoneyUseCase;
import money.application.AccumulatedPoint.UseAccumulatedMoneyUseCase.dto.UseAccumulatedMoneyUseCaseRequest;
import money.config.ApiErrorCodeExample;
import money.core.BaseResponse;
import money.core.error.enums.BadRequestCode;
import money.core.error.enums.NotFoundCode;
import money.interfaces.IResponse;
import money.presentation.request.AdditionalAccumulatedMoneyRequest;
import money.presentation.request.FindUsageHistoryRequest;
import money.presentation.request.UseAccumulatedMoneyRequest;
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
    private final AdditionalAccumulatedMoneyUseCase additionalAccumulatedMoneyUseCase;
    private final UseAccumulatedMoneyUseCase useAccumulatedMoneyUseCase;

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

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PostMapping(value = "/add", produces = "application/json; charset=UTF-8")
    public ResponseEntity<BaseResponse> additionalMoney(
            @Valid @RequestBody AdditionalAccumulatedMoneyRequest additionalAccumulatedMoneyRequest
    ) {

        additionalAccumulatedMoneyUseCase.execute(
                AdditionalAccumulatedMoneyUseCaseRequest.init(
                        additionalAccumulatedMoneyRequest.getPoint(),
                        additionalAccumulatedMoneyRequest.getUuid()
                )
        );

        return ResponseEntity.ok(new BaseResponse());
    }

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PostMapping(value = "/use", produces = "application/json; charset=UTF-8")
    public ResponseEntity<BaseResponse> use(
            @Valid @RequestBody UseAccumulatedMoneyRequest useAccumulatedMoneyRequest
    ) {
        useAccumulatedMoneyUseCase.execute(
                UseAccumulatedMoneyUseCaseRequest.init(
                        useAccumulatedMoneyRequest.getPoint(),
                        useAccumulatedMoneyRequest.getUuid()
                )
        );

        return ResponseEntity.ok(new BaseResponse());
    }
}
