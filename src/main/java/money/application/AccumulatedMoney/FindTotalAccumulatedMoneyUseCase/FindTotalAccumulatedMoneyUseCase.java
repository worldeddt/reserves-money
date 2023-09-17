package money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.dto.FindTotalAccumulatedMoneyUseCaseResponse;
import money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.vo.FindTotalAccumulatedMoneyUseCaseRequestBody;
import money.application.AccumulatedMoney.FindTotalAccumulatedMoneyUseCase.vo.FindTotalAccumulatedMoneyUseCaseResponseBody;
import money.core.exceptions.CommonException;
import money.domain.entity.User;
import money.domain.enums.UserStatus;
import money.infra.UserRepository;
import money.interfaces.IRequest;
import money.interfaces.IResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static money.core.error.enums.NotFoundCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class FindTotalAccumulatedMoneyUseCase {
    private UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    public IResponse<FindTotalAccumulatedMoneyUseCaseResponseBody> execute(IRequest<FindTotalAccumulatedMoneyUseCaseRequestBody> iRequest) {

        FindTotalAccumulatedMoneyUseCaseRequestBody conditions = iRequest.getConditions();

        User user = userRepository.findUserByUuidAndStatus(UUID.fromString(conditions.getUuid()), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        return FindTotalAccumulatedMoneyUseCaseResponse.init(
                user.getPoint()
        );
    }
}
