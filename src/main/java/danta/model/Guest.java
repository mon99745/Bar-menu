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
 * 비회원
 */
@Schema(description = "비회원")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(indexes = {
        @Index(name = "idx_guest_name", columnList = "name"),
        @Index(name = "idx_guest_id", columnList = "id", unique = true),
        @Index(name = "idx_guest_state", columnList = "state"),
        @Index(name = "idx_guest_reg_date", columnList = "regDate"),
        @Index(name = "idx_guest_mod_date", columnList = "modDate")})
@org.hibernate.annotations.Table(appliesTo = Guest.TABLE_NAME, comment = Guest.TABLE_DESC)
public class Guest {
    public static final String NAME_SPACE = "Guest";
    public static final String TABLE_NAME = "guest";
    public static final String TABLE_DESC = "비회원";

    /**
     * TODO:
     * 일련 번호 사용
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected String id;

    /**
     * 비회원 상태
     */
    @JsonProperty(index = 10)
    @Schema(description = "비회원 상태")
    @Column(length = 100)
    protected String status;

}