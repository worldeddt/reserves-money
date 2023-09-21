package money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase;

import money.domain.entity.AccumulatedMoneyHistory;
import money.domain.enums.AccumulateStatus;
import money.infra.AccumulatedMoneyRepository;
import money.infra.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class FindAccumulatedMoneyHistoryUseCaseTest {
    @Mock
    private AccumulatedMoneyRepository accumulatedMoneyRepository;

    @Test
    void uuid가_올바르지_않으면_적립금_충전_목록을_찾을_수_없어야_한다() {
        //given (맞지 않은 uuid)
        String uuid = "1ee57ce3-3542-6c0e-be4a-018eca93200";

        //when
        Optional<List<AccumulatedMoneyHistory>> accumulatedMoneyHistoryPage = accumulatedMoneyRepository.
                findAllByUserUuidAndStatusOrderByIndexAsc(UUID.fromString(uuid), AccumulateStatus.ACCUMUL.name());

        //then
        assertFalse(accumulatedMoneyHistoryPage.isPresent());
    }

    @Test
    void 맞지_않은_uuid일_경우_적립금_이용내역을_불러_올_수_없어야_한다() {

        //given
        String uuid = "1ee57ce3-3542-6c0e-be4a-018eca932050";

        //when
        Optional<List<AccumulatedMoneyHistory>> accumulatedMoneyHistoryPage = accumulatedMoneyRepository.
                findAllByUserUuidAndStatusOrderByIndexDesc(UUID.fromString(uuid), AccumulateStatus.ACCUMUL.name());

        //then
        assertFalse(accumulatedMoneyHistoryPage.isPresent());
    }
}