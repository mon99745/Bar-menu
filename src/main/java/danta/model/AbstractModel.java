package danta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.bind.v2.TODO;
import danta.converter.DateTimeFormatConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractModel {

    /**
     * 등록일시(형식: yyyy-MM-dd HH:mm:ss)
     */
    @CreatedDate // 자동 등록
    @JsonProperty(index = 910)
    @Schema(description = "등록일시(형식: yyyy-MM-dd HH:mm:ss)", hidden = true)
    @Column(updatable = false)
    private LocalDateTime regDate;

    /**
     * 수정일시(형식: yyyy-MM-dd HH:mm:ss)
     */
    @LastModifiedDate // 자동 수정
    @JsonProperty(index = 920)
    @Schema(description = "수정일시(형식: yyyy-MM-dd HH:mm:ss)", hidden = true)
    @Column
    private LocalDateTime modDate;
}
