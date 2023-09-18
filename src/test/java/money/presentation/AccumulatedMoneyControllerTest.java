package money.presentation;

import money.core.BaseResponse;
import money.presentation.request.AdditionalAccumulatedMoneyRequest;
import money.presentation.request.FindUsageHistoryRequest;
import money.presentation.request.UseAccumulatedMoneyRequest;
import money.presentation.response.FindUsageHistoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.http.HttpStatus.OK;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccumulatedMoneyControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void 회원_적립금_충전_사용_목록을_불러올_수_있어야한다() {
        //given
        String requestUrl = "/v1/accumulated-point/usage-history?page=0&size=5";
        FindUsageHistoryRequest findUsageHistoryRequest =
                new FindUsageHistoryRequest("1ee553f7-ab7e-6ac5-9947-3d3eb0a1fdda");

        //when
        ResponseEntity<FindUsageHistoryResponse> findUsageHistoryResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, findUsageHistoryRequest, FindUsageHistoryResponse.class);

        //then
        assertEquals(OK, findUsageHistoryResponseResponseEntity.getStatusCode());
        assertNotEquals(null, findUsageHistoryResponseResponseEntity.getBody().getPageResponse());
        assertNotEquals(null, findUsageHistoryResponseResponseEntity.getBody().getAccumulatedPointHistoryDomainList());
    }

    @Test
    void 회원은_적립금을_적립할_수_있어야한다() {
        //given
        String requestUrl = "/v1/accumulated-point/add";
        AdditionalAccumulatedMoneyRequest additionalAccumulatedMoneyRequest =
                new AdditionalAccumulatedMoneyRequest(
                        "1ee55693-98a5-60df-9d40-69c770770bf9", 20, "회원 리뷰 리워드");

        //when
        ResponseEntity<BaseResponse> baseResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, additionalAccumulatedMoneyRequest, BaseResponse.class);

        //then
        assertEquals(OK, baseResponseResponseEntity.getStatusCode());

        String url = "/v1/accumulated-point/usage-history?page=0&size=5";
        FindUsageHistoryRequest findUsageHistoryRequest =
                new FindUsageHistoryRequest("1ee55693-98a5-60df-9d40-69c770770bf9");

        ResponseEntity<FindUsageHistoryResponse> findUsageHistoryResponseResponseEntity =
                testRestTemplate.postForEntity(url, findUsageHistoryRequest, FindUsageHistoryResponse.class);

        assertNotEquals(null, findUsageHistoryResponseResponseEntity.getBody());

        assertEquals(findUsageHistoryResponseResponseEntity.getBody().
                getAccumulatedPointHistoryDomainList().get(0).getPrice(), 20);
    }

    @Test
    void 포인트_사용_처리를_확인할_수_있어야한다() {
        //given
        String requestUrl = "/v1/accumulated-point/use";
        UseAccumulatedMoneyRequest useAccumulatedMoneyRequest =
                new UseAccumulatedMoneyRequest(
                        "1ee55693-98a5-60df-9d40-69c770770bf9", 100, "회원 리뷰 리워드 사용");

        //when
        ResponseEntity<BaseResponse> baseResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, useAccumulatedMoneyRequest, BaseResponse.class);

        //then
        assertEquals(OK, baseResponseResponseEntity.getStatusCode());

        String url = "/v1/accumulated-point/usage-history?page=0&size=5";
        FindUsageHistoryRequest findUsageHistoryRequest =
                new FindUsageHistoryRequest("1ee55693-98a5-60df-9d40-69c770770bf9");

        ResponseEntity<FindUsageHistoryResponse> findUsageHistoryResponseResponseEntity =
                testRestTemplate.postForEntity(url, findUsageHistoryRequest, FindUsageHistoryResponse.class);

        assertNotEquals(null, findUsageHistoryResponseResponseEntity.getBody());

        assertEquals(findUsageHistoryResponseResponseEntity.getBody().
                getAccumulatedPointHistoryDomainList().get(0).getPrice(), 100);
    }

    @Test
    void 회원의_포인트는_마이너스가_될_수_없다() {
        //given
        // 1000point 보다 작을 경우
        String requestUrl = "/v1/accumulated-point/use";
        UseAccumulatedMoneyRequest useAccumulatedMoneyRequest =
                new UseAccumulatedMoneyRequest(
                        "1ee55693-98a5-60df-9d40-69c770770bf9", 1000, "회원 리뷰 리워드 사용");

        //when
        ResponseEntity<BaseResponse> baseResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, useAccumulatedMoneyRequest, BaseResponse.class);

        //then
        assertNotEquals(OK, baseResponseResponseEntity.getStatusCode());
    }
}