package danta.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 회원 정보 ENTITY
 */
@Schema(description = "회원")
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Embeddable
@DynamicInsert
@DynamicUpdate
@Table(name = "UserInfo", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id", unique = true),
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_password", columnList = "password"),
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_reg_date", columnList = "regDate"),
        @Index(name = "idx_mod_date", columnList = "modDate")})
public class User extends AbstractModel {

    /**
     * 회원 식별 번호
     */
    @Id
    @Column(name = "user_id", nullable = false)
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    /**
     * 회원 ID
     * TODO 암호화
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
     * 회원 상태
     * TODO ENUM
     */
    @JsonProperty(index = 40)
    @Schema(description = "회원 상태")
    @Column(length = 100)
    protected String status;

}

