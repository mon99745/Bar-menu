package com.example.bmm.domain.guest;


import com.example.bmm.domain.AbstractModel;
import com.example.bmm.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 비회원 정보 ENTITY
 */
@Schema(description = "비회원")
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "GuestInfo", indexes = {
        @Index(name = "idx_guest_id", columnList = "guest_id", unique = true),
        @Index(name = "idx_guest_status", columnList = "status"),
        @Index(name = "idx_guest_reg_date", columnList = "regDate"),
        @Index(name = "idx_guest_mod_date", columnList = "modDate")})

public class Guest extends AbstractModel {

    /**
     * 게스트 ID
     * Session id 의 Hash 값을 사용
     */
    @Id
    @Column(name = "guest_id", nullable = false)
    protected Long id;

    /**
     * 비회원 상태
     */
    @JsonProperty(index = 10)
    @Schema(description = "비회원 상태")
    @Column(length = 100)
    protected boolean status;

    /**
     * 권한
     */
    @Enumerated(EnumType.STRING)
    @Schema(description = "권한")
    @Column(nullable = false)
    private Role role;

}