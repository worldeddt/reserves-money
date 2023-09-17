package money.infra;


import money.domain.entity.AccumulatedPointHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccumulatedPointRepository extends JpaRepository<AccumulatedPointHistory, Integer> {

//    Optional<List<AccumulatedPointHistory>> findAllByUserUuidOrderByIndexDesc(String uuid, Pageable pageable);

    Page<AccumulatedPointHistory> findAllByUserUuidOrderByIndexDesc(String uuid, Pageable pageable);
}
