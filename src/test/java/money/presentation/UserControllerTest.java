package money.presentation;

import money.presentation.request.FindUsageHistoryRequest;
import money.presentation.request.SignUpUserRequest;
import money.presentation.response.FindUsageHistoryResponse;
import money.presentation.response.SignUpUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void 회원_등록_후_uuid_를_받을_수_있어야_한다() {
        //given
        String requestUrl = "/v1/user/signUp";
        SignUpUserRequest signUpUserRequest = new SignUpUserRequest(
                "xodid7986", "1111dd2222@"
        );

        //when
        ResponseEntity<SignUpUserResponse> signUpUserResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, signUpUserRequest, SignUpUserResponse.class);

        //then
        assertEquals(OK, signUpUserResponseResponseEntity.getStatusCode());
        assertNotEquals(null, signUpUserResponseResponseEntity.getBody().getUuid());
    }

    @Test
    void 비밀번호는_영소문자를_최소_2개_이상_포함해야_한다() {

        //given
        String requestUrl = "/v1/user/signUp";
        SignUpUserRequest signUpUserRequest = new SignUpUserRequest(
                "xodid7986", "1111d2222@"
        );

        //when
        ResponseEntity<SignUpUserResponse> signUpUserResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, signUpUserRequest, SignUpUserResponse.class);

        //then
        assertNotEquals(OK, signUpUserResponseResponseEntity.getStatusCode());
    }

    @Test
    void 아이디는_5자리_이상_이어야한다() {
        //given
        String requestUrl = "/v1/user/signUp";
        SignUpUserRequest signUpUserRequest = new SignUpUserRequest(
                "xodi", "1111dq2222@"
        );

        //when
        ResponseEntity<SignUpUserResponse> signUpUserResponseResponseEntity =
                testRestTemplate.postForEntity(requestUrl, signUpUserRequest, SignUpUserResponse.class);

        //then
        assertNotEquals(OK, signUpUserResponseResponseEntity.getStatusCode());
    }
}