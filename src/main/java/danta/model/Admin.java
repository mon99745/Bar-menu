package danta.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 관리자 정보 ENTITY
 */
@Schema(description = "관리자")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "adminInfo", indexes = {
        @Index(name = "idx_admin_id", columnList = "admin_id", unique = true),
        @Index(name = "idx_admin_password", columnList = "password"),
        @Index(name = "idx_admin_name", columnList = "name"),
        @Index(name = "idx_admin_status", columnList = "status"),
        @Index(name = "idx_admin_address", columnList = "address"),
        @Index(name = "idx_admin_reg_date", columnList = "regDate"),
        @Index(name = "idx_admin_mod_date", columnList = "modDate")})

public class Admin extends AbstractModel {

    /**
     * 관리자 ID
     */
    @Id
    @Schema(description = "관리자 ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "admin_id", nullable = false)
    protected Long id;

    /**
     * 관리자 비밀번호
     */
    @JsonProperty(index = 10)
    @Schema(description = "관리자 비밀번호")
    @Column(length = 100)
    protected String password;

    /**
     * 관리자 이름
     */
    @JsonProperty(index = 10)
    @Schema(description = "관리자 이름")
    @Column(length = 100)
    protected String name;

    /**
     * 관리자 상태
     * TODO 상태정보를 ENUM 처리
     */
    @JsonProperty(index = 10)
    @Schema(description = "관리자 상태")
    @Column(length = 100)
    protected String status;

    /**
     * 관리 주소
     * (지점 주소)
     * TODO Stream 사용
     */
    @JsonProperty(index = 10)
    @Schema(description = "관리 주소")
    @Column(length = 100)
    protected String address;


}