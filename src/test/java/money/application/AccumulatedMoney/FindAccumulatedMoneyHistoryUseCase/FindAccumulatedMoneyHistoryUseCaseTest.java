package money.application.AccumulatedMoney.FindAccumulatedMoneyHistoryUseCase;

import money.application.AccumulatedMoney.AdditionalAccumulatedMoneyUseCase.AdditionalAccumulatedMoneyUseCase;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static money.core.error.enums.NotFoundCode.RESERVESE_MONEY_LIST_NOT_FOUND;
import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class FindAccumulatedMoneyHistoryUseCaseTest {

    @InjectMocks
    private FindAccumulatedMoneyHistoryUseCase findAccumulatedMoneyHistoryUseCase;


    @Mock
    private UserRepository userRepository;

    @Mock
    private AccumulatedMoneyRepository accumulatedMoneyRepository;

    @Test
    void uuid가_올바르지_않으면_적립금_충전_목록을_찾을_수_없어야_한다() {
        //given
        String uuid = "1ee55693-98a5-29dd-9d40-69c770770bf9";


        //when
        Optional<List<AccumulatedMoneyHistory>> accumulatedMoneyHistoryPage = accumulatedMoneyRepository.
                findAllByUserUuidAndStatusOrderByIndexAsc(UUID.fromString(uuid), AccumulateStatus.ACCUMUL.name());


        //then
        assertFalse(accumulatedMoneyHistoryPage.isPresent());
    }
}