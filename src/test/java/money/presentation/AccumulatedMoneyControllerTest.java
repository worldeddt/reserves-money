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
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccumulatedMoneyControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void 적립금_이력이_없을_경우_회원_적립금_충전_사용_목록을_불러올_수_없어야한다() {
        //given
        String requestUrl = "/v1/accumulating-point/usage-history?page=0&size=5";
        FindUsageHistoryRequest findUsageHistoryRequest =
                new FindUsageHistoryRequest("1ee57ce3-3542-6c0e-be4a-018eca932050");

        //when
        ResponseEntity<FindUsageHistoryResponse> findUsageHistoryResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, findUsageHistoryRequest, FindUsageHistoryResponse.class);

        //then
        assertNull(findUsageHistoryResponseResponseEntity.getBody().getPageResponse());
        assertNull(findUsageHistoryResponseResponseEntity.getBody().getAccumulatedPointHistoryDomainList());
    }

    @Test
    void 회원은_적립금을_적립할_수_있어야한다() {
        //given
        String requestUrl = "/v1/accumulating-point/";
        AdditionalAccumulatedMoneyRequest additionalAccumulatedMoneyRequest =
                new AdditionalAccumulatedMoneyRequest(
                        "1ee57ce3-3542-6c0e-be4a-018eca932050", 500, "회원 리뷰 리워드");

        //when
        ResponseEntity<BaseResponse> baseResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, additionalAccumulatedMoneyRequest, BaseResponse.class);

        //then
        assertNotNull(baseResponseResponseEntity.getStatusCode());

        HttpStatusCode statusCode = baseResponseResponseEntity.getStatusCode();
        assertEquals(OK, baseResponseResponseEntity.getStatusCode());

        String url = "/v1/accumulating-point/usage-history?page=0&size=5";
        FindUsageHistoryRequest findUsageHistoryRequest =
                new FindUsageHistoryRequest("1ee57ce3-3542-6c0e-be4a-018eca932050");

        ResponseEntity<FindUsageHistoryResponse> findUsageHistoryResponseResponseEntity =
                testRestTemplate.getForEntity(url, FindUsageHistoryResponse.class, findUsageHistoryRequest);

        assertNotNull(findUsageHistoryResponseResponseEntity);
        System.out.println("findUsageHistoryResponseResponseEntity :"+findUsageHistoryResponseResponseEntity);

        assertNotEquals(Objects.requireNonNull(findUsageHistoryResponseResponseEntity.getBody()).
        getAccumulatedPointHistoryDomainList().size(), 500);
    }

    @Test
    void 포인트_사용_처리를_확인할_수_있어야한다() {
        //given
        String requestUrl = "/v1/accumulating-point/";
        UseAccumulatedMoneyRequest useAccumulatedMoneyRequest =
                new UseAccumulatedMoneyRequest(
                        "1ee57ce3-3542-6c0e-be4a-018eca932050", 50, "회원 리뷰 리워드 사용");

        //when
        testRestTemplate.put(requestUrl, useAccumulatedMoneyRequest);

        //then
        String url = "/v1/accumulating-point/usage-history?page=0&size=5";
        FindUsageHistoryRequest findUsageHistoryRequest =
                new FindUsageHistoryRequest("1ee57ce3-3542-6c0e-be4a-018eca932050");

        ResponseEntity<FindUsageHistoryResponse> findUsageHistoryResponseResponseEntity =
                testRestTemplate.getForEntity(url, FindUsageHistoryResponse.class, findUsageHistoryRequest);

        assertNotNull(findUsageHistoryResponseResponseEntity.getBody());

        System.out.println("body : "+findUsageHistoryResponseResponseEntity.getBody());
        assertEquals(findUsageHistoryResponseResponseEntity.getBody().
                getAccumulatedPointHistoryDomainList().get(0).getPrice(), 50);
    }

    @Test
    void 회원의_포인트는_마이너스가_될_수_없다() {
        //given
        // 1000point 보다 작을 경우
        String requestUrl = "/v1/accumulating-point/use";
        UseAccumulatedMoneyRequest useAccumulatedMoneyRequest =
                new UseAccumulatedMoneyRequest(
                        "1ee57ce3-3542-6c0e-be4a-018eca932050", 1000, "회원 리뷰 리워드 사용");

        //when
        ResponseEntity<BaseResponse> baseResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, useAccumulatedMoneyRequest, BaseResponse.class);

        //then
        assertNotEquals(OK, baseResponseResponseEntity.getStatusCode());
    }
}