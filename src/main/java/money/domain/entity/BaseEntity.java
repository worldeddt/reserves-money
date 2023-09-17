package money.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Comment("등록일시")
    @Column(name = "register_datetime", columnDefinition = "datetime default '0000-00-00 00:00:00'",
            updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime registerDatetime;

    @Comment("갱신일시")
    @Column(name = "update_datetime", columnDefinition = "datetime default '0000-00-00 00:00:00'")
    @LastModifiedDate
    private LocalDateTime updateDatetime;
}
