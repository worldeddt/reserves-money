package money.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Entity
@Table(name= "reserves_money_history")
public class AccumulatedPointHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rmh_index")
    private Integer index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "rmu_index", columnDefinition = "int",  nullable = false, name = "rmh_rmu_index")
    private User user;

    @Comment("적립/사용 상태 (ACCUMUL:적립, USED:사용)")
    @Column(name="rmh_status", columnDefinition = "enum('ACCUMUL', 'USED') default 'ACCUMUL'", nullable = false)
    private String status;

    @Comment("적립/사용 금액")
    @Column(name="rmh_price", columnDefinition = "int default 0", nullable = false)
    private Integer price;
}
