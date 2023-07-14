package com.example.bmm.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@Data
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
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    /**
     * 수정일시(형식: yyyy-MM-dd HH:mm:ss)
     */
    @LastModifiedDate // 자동 수정
    @JsonProperty(index = 920)
    @Schema(description = "수정일시(형식: yyyy-MM-dd HH:mm:ss)", hidden = true)
    @Column(name = "modDate")
    private LocalDateTime modDate;
}
