package money.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Data
@Entity
@Table(name= "accumulated_money_history")
public class AccumulatedMoneyHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rmh_index")
    private Integer index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "rmu_index", columnDefinition = "int",  nullable = false, name = "rmh_rmu_index")
    @ToString.Exclude
    private User user;

    @Comment("적립/사용 상태 (ACCUMUL:적립, USED:사용)")
    @Column(name="rmh_status", columnDefinition = "enum('ACCUMUL', 'USED') default 'ACCUMUL'", nullable = false)
    private String status;

    @Comment("적립/사용 금액")
    @Column(name="rmh_price", columnDefinition = "int default 0", nullable = false)
    private Integer price;

    public void setOnUser(User user) {
        if (this.user != null) {  // 기존에 user 와 연관관계가 있다면 끊는다 > 안할 시 에러 발생.
            this.user.getAccumulatedPointHistories().remove(this);
        }

        this.user = user;
        user.getAccumulatedPointHistories().add(this);
    }
}
