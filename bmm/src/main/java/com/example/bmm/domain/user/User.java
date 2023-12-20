package com.example.bmm.domain.user;


import com.example.bmm.domain.AbstractModel;
import com.example.bmm.model.enums.Role;
import com.example.bmm.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 회원 정보 ENTITY
 */
@Schema(description = "회원")
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "UserInfo", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id", unique = true),
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_user_password", columnList = "password"),
        @Index(name = "idx_user_name", columnList = "name"),
        @Index(name = "idx_user_status", columnList = "status"),
        @Index(name = "idx_user_reg_date", columnList = "regDate"),
        @Index(name = "idx_user_mod_date", columnList = "modDate")})
public class User extends AbstractModel {

    /**
     * 회원 식별 번호
     */
    @Id
    @Column(name = "user_id", nullable = false)
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * 회원 상태
     * 활성화 OR 비활성화
     */
    @Column(length = 100)
    @ApiModelProperty(hidden = true)
    protected Status status;

    /**
     * 회원 ID
     */
    @JsonProperty(index = 10)
    @Schema(description = "회원 ID")
    @Column(length = 100, nullable = false)
    protected String username;

    /**
     * 회원 비밀번호
     * TODO 암호화
     */
    @JsonProperty(index = 20)
    @Schema(description = "회원 비밀번호")
    @Column(length = 100)
    protected String password;

    /**
     * 회원 이름
     */
    @JsonProperty(index = 30)
    @Schema(description = "회원 이름")
    @Column(length = 100)
    protected String name;

    /**
     * 권한
     */
    @Enumerated(EnumType.STRING)
    @Schema(description = "권한")
    @Column(nullable = false)
    private Role role;

    /**
     * 권한 메소드
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

}

