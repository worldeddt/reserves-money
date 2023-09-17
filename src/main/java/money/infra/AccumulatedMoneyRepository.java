package money.infra;


import money.domain.entity.AccumulatedMoneyHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccumulatedMoneyRepository extends JpaRepository<AccumulatedMoneyHistory, Integer> {
    Page<AccumulatedMoneyHistory> findAllByUserUuidOrderByIndexDesc(UUID uuid, Pageable pageable);
}
