package money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase;

import money.application.AccumulatedMoney.UseAccumulatedMoneyUseCase.dto.UseAccumulatedMoneyUseCaseRequest;
import money.core.exceptions.CommonException;
import money.domain.entity.AccumulatedMoneyHistory;
import money.domain.entity.User;
import money.domain.enums.AccumulateStatus;
import money.domain.enums.UserStatus;
import money.infra.AccumulatedMoneyRepository;
import money.infra.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static money.core.error.enums.NotFoundCode.RESERVESE_MONEY_LIST_NOT_FOUND;
import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UseAccumulatedMoneyUseCaseTest {
    @Mock
    private AccumulatedMoneyRepository accumulatedMoneyRepository;

    @Test
    void 사용내역을_불러올_수_있어야_한다() {

        //given
        String uuid = "1ee57ce3-3542-6c0e-be4a-018eca932050";

        //when
        Optional<List<AccumulatedMoneyHistory>> accumulatedMoneyHistories =
                accumulatedMoneyRepository.findAllByUserUuidAndStatusOrderByIndexAsc(
                                UUID.fromString(uuid), AccumulateStatus.USED.name());

        //then
        assertFalse(accumulatedMoneyHistories.isPresent());
    }

}