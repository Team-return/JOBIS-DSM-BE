package team.returm.jobis.global.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(columnDefinition = "DATETIME(6)", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
