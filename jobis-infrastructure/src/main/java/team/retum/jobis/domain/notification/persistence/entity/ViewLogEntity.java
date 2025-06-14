package team.retum.jobis.domain.notification.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_view_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private Long userId;

    private LocalDateTime viewedAt;

    public ViewLogEntity(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
        this.viewedAt = LocalDateTime.now();
    }
}
