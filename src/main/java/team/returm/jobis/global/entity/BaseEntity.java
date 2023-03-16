package team.returm.jobis.global.entity;

import com.fasterxml.uuid.Generators;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID id = Generators.timeBasedGenerator().generate();

    @CreatedDate
    @Column(columnDefinition = "DATETIME(6)", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
