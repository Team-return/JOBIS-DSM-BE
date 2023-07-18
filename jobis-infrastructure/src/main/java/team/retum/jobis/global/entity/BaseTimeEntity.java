package team.retum.jobis.global.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(columnDefinition = "DATETIME(6)", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
