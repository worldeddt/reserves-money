package money.presentation;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.AdditionalAccumulatedMoneyUseCase;
import money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.dto.AdditionalAccumulatedMoneyUseCaseRequest;
import money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.FindAccumulatedMoneyHistoryUseCase;
import money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.dto.FindAccumulatedMoneyHistoryUseCaseRequest;
import money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase.vo.FindAccumulatedMoneyHistoryUseCaseResponseBody;
import money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.FindTotalAccumulatedMoneyUseCase;
import money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.dto.FindTotalAccumulatedMoneyUseCaseRequest;
import money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.vo.FindTotalAccumulatedMoneyUseCaseResponseBody;
import money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase.UseAccumulatedMoneyUseCase;
import money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase.dto.UseAccumulatedMoneyUseCaseRequest;
import money.config.ApiErrorCodeExample;
import money.core.BaseResponse;
import money.core.error.enums.BadRequestCode;
import money.core.error.enums.NotFoundCode;
import money.interfaces.IResponse;
import money.presentation.request.AdditionalAccumulatedMoneyRequest;
import money.presentation.request.FindTotalAccumulatedMoneyRequest;
import money.presentation.request.FindUsageHistoryRequest;
import money.presentation.request.UseAccumulatedMoneyRequest;
import money.presentation.response.FindTotalAccumulatedMoneyResponse;
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
public class AccumulatedMoneyController {
    private final FindAccumulatedMoneyHistoryUseCase findAccumulatedPointHistoryUseCase;
    private final AdditionalAccumulatedMoneyUseCase additionalAccumulatedMoneyUseCase;
    private final UseAccumulatedMoneyUseCase useAccumulatedMoneyUseCase;
    private final FindTotalAccumulatedMoneyUseCase findTotalAccumulatedMoneyUseCase;

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PutMapping(value = "/usage-history", produces = "application/json; charset=UTF-8")
    public ResponseEntity<FindUsageHistoryResponse> get(
            @Valid @RequestBody FindUsageHistoryRequest findUsageHistoryRequest,
            @PageableDefault(page = 1, sort = "index", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {

        IResponse<FindAccumulatedMoneyHistoryUseCaseResponseBody> response = findAccumulatedPointHistoryUseCase.execute(
                FindAccumulatedMoneyHistoryUseCaseRequest.init(
                        findUsageHistoryRequest.getUuid(),
                        pageable
                )
        );

        FindAccumulatedMoneyHistoryUseCaseResponseBody responseBody = response.getResponse();

        return ResponseEntity.ok(FindUsageHistoryResponse.init(
                responseBody.getAccumulatedMoneyHistoryDomains(),
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
                        additionalAccumulatedMoneyRequest.getPurpose(),
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
                        useAccumulatedMoneyRequest.getPurpose(),
                        useAccumulatedMoneyRequest.getUuid()
                )
        );

        return ResponseEntity.ok(new BaseResponse());
    }

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PostMapping(value = "/total", produces = "application/json; charset=UTF-8")
    public ResponseEntity<FindTotalAccumulatedMoneyResponse> totalMoney(
            @Valid @RequestBody FindTotalAccumulatedMoneyRequest findTotalAccumulatedMoneyRequest
    ) {

        IResponse<FindTotalAccumulatedMoneyUseCaseResponseBody>
                findTotalAccumulatedMoneyUseCaseResponseBodyIResponse =
                findTotalAccumulatedMoneyUseCase.execute(
                FindTotalAccumulatedMoneyUseCaseRequest.init(
                        findTotalAccumulatedMoneyRequest.getUuid()
                )
        );

        FindTotalAccumulatedMoneyUseCaseResponseBody response =
                findTotalAccumulatedMoneyUseCaseResponseBodyIResponse.getResponse();

        return ResponseEntity.ok(FindTotalAccumulatedMoneyResponse.init(response.getPoint()));
    }
}
