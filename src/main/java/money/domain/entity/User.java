package money.domain.entity;


import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name= "accumulated_money_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rmu_index")
    private Integer index;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<AccumulatedMoneyHistory> accumulatedPointHistories;

    @Comment("아이디")
    @Column(name = "rmu_id", columnDefinition = "varchar(50) default''", nullable = false)
    private String id;

    @Comment("비밀번호")
    @Column(name = "rmu_password", columnDefinition = "varchar(500) default ''", nullable = false)
    private String password;

    @Comment("유저 전용 유니크 번호")
    @Column(name = "rmu_user_uuid", columnDefinition = "varchar(1000) default ''", nullable = false, unique = true)
    private UUID uuid;

    @Comment("유저 상태")
    @Column(name="rmu_status", columnDefinition = "enum('ACTIVE', 'DELETED') default 'ACTIVE'", nullable = false)
    private String status;

    @Comment("유저 포인트")
    @Column(name = "rmu_point", columnDefinition = "bigint default 0", nullable = false)
    private Long point;

    @PrePersist
    public void createUuid() {
        this.uuid = UuidCreator.getTimeOrdered();
    }
}

