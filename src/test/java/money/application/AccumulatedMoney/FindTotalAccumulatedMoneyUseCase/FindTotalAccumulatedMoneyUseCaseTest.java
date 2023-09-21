package money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase;

import money.core.exceptions.CommonException;
import money.domain.entity.User;
import money.domain.enums.UserStatus;
import money.infra.AccumulatedMoneyRepository;
import money.infra.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindTotalAccumulatedMoneyUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void  유저가_조회되지_않으면_총_보유액을_불러올_수_없다() {

        String uuid = "1ee57ce3-3542-6c0e-be4a-018eca932050";

        Optional<User> userByUuidAndStatus = userRepository.findUserByUuidAndStatus(UUID.fromString(
                uuid), UserStatus.ACTIVE.name());

        assertFalse(userByUuidAndStatus.isPresent());
    }
}