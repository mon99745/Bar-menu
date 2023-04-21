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
 * 관리자
 */
@Schema(description = "관리자")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(indexes = {
        @Index(name = "idx_admin_id", columnList = "id", unique = true),
        @Index(name = "idx_admin_name", columnList = "name"),
        @Index(name = "idx_admin_price", columnList = "price"),
        @Index(name = "idx_admin_image", columnList = "image"),
        @Index(name = "idx_admin_description", columnList = "description"),
        @Index(name = "idx_admin_status", columnList = "status"),
        @Index(name = "idx_admin_reg_date", columnList = "regDate"),
        @Index(name = "idx_admin_mod_date", columnList = "modDate")})
@org.hibernate.annotations.Table(appliesTo = Admin.TABLE_NAME, comment = Admin.TABLE_DESC)
public class Admin {
    public static final String NAME_SPACE = "Admin";
    public static final String TABLE_NAME = "admin";
    public static final String TABLE_DESC = "관리자";

    /**
     * 관리자 ID
     */
    @Id
    @Schema(description = "관리자 ID")
    @Column(name = "id", nullable = false)
    protected String id;

    /**
     * 관리자 이름
     */
    @JsonProperty(index = 10)
    @Schema(description = "관리자 이름")
    @Column(length = 100)
    protected String name;

}