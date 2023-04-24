package danta.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import danta.common.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.users.AbstractUser;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 회원
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
@Table(indexes = {
        @Index(name = "idx_user_id", columnList = "user_id", unique = true),
        @Index(name = "idx_user_name", columnList = "name"),
        @Index(name = "idx_user_status", columnList = "status"),
        @Index(name = "idx_user_reg_date", columnList = "regDate"),
        @Index(name = "idx_user_mod_date", columnList = "modDate")})
@org.hibernate.annotations.Table(appliesTo = User.TABLE_NAME, comment = User.TABLE_DESC)
public class User extends AbstractModel {
    public static final String NAME_SPACE = "User";
    public static final String TABLE_NAME = "user";
    public static final String TABLE_DESC = "회원";

    /**
     * 회원 ID
     */
    @Id
    @Schema(description = "회원 ID")
    @Column(name = "user_id",nullable = false)
    protected String id;

    /**
     * 회원 비밀번호
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

