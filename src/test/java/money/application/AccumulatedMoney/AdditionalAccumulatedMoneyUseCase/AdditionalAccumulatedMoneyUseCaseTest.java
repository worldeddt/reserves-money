package money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase;

import money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.dto.AdditionalAccumulatedMoneyUseCaseRequest;
import money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.vo.AdditionalAccumulatedMoneyUseCaseRequestBody;
import money.core.exceptions.CommonException;
import money.domain.entity.User;
import money.domain.enums.UserStatus;
import money.infra.AccumulatedMoneyRepository;
import money.infra.UserRepository;
import money.presentation.request.AdditionalAccumulatedMoneyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class AdditionalAccumulatedMoneyUseCaseTest {
    @InjectMocks
    private AdditionalAccumulatedMoneyUseCase additionalAccumulatedMoneyUseCase;


    @Mock
    private UserRepository userRepository;

    @Mock
    private AccumulatedMoneyRepository accumulatedMoneyRepository;

    @Test
    void 예외처리_없이_저장이_이루어져야_한다() {
        //given
        String uuid = "1ee5569a-58bf-6e23-97bc-4bd917f86155";

        //when
        Optional<User> userByUuidAndStatus = userRepository.findUserByUuidAndStatus(
                UUID.fromString(uuid),
                UserStatus.ACTIVE.name());

        //then
        assertFalse(userByUuidAndStatus.isPresent());
    }

    @Test
    void 데이터를_새로_추가할_경우_올바른_응답값이_입력되어야_한다() {
        //given
        AdditionalAccumulatedMoneyUseCaseRequest additionalAccumulatedMoneyUseCaseRequest =
                AdditionalAccumulatedMoneyUseCaseRequest.init(
                100,
                "리뷰 이벤트 리워드",
                ""
        );

        //when
        additionalAccumulatedMoneyUseCase.execute(additionalAccumulatedMoneyUseCaseRequest);
        //then

        assertNull(userRepository.findUserByUuidAndStatus(
                UUID.fromString(additionalAccumulatedMoneyUseCaseRequest.getConditions().getUuid()), UserStatus.ACTIVE.name())
        );
    }
}