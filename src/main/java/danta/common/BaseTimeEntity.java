package danta.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 날짜 필드를 자동으로 관리
 */

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 날짜 필드도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate //생성할 때 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate //수정할 때 자동저장
    private LocalDateTime modifiedDate;
}