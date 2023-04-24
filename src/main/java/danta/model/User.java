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
 * 회원 정보 ENTITY
 */
@Schema(description = "회원")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Embeddable
@DynamicInsert
@DynamicUpdate
@Table(name = "UserInfo", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id", unique = true),
        @Index(name = "idx_user_password", columnList = "password"),
        @Index(name = "idx_user_name", columnList = "name"),
        @Index(name = "idx_user_status", columnList = "status"),
        @Index(name = "idx_user_reg_date", columnList = "regDate"),
        @Index(name = "idx_user_mod_date", columnList = "modDate")})
public class User extends AbstractModel {

    /**
     * 회원 ID
     */
    @Id
    @Schema(description = "회원 ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id",nullable = false)
    protected String id;

    /**
     * 회원 비밀번호
     * TODO 암호화 함수 추가
     */
    @JsonProperty(index = 10)
    @Schema(description = "회원 비밀번호")
    @Column(length = 100)
    protected String password;

    /**
     * 회원 이름
     */
    @JsonProperty(index = 10)
    @Schema(description = "회원 이름")
    @Column(length = 100)
    protected String name;

    /**
     * 회원 상태
     */
    @JsonProperty(index = 10)
    @Schema(description = "회원 상태")
    @Column(length = 100)
    protected String status;

}

