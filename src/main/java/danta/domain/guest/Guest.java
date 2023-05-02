package danta.domain.guest;


import com.fasterxml.jackson.annotation.JsonProperty;
import danta.domain.AbstractModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

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
     * TODO: 일련 번호 사용
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id", nullable = false)
    protected Long id;

    /**
     * 비회원 상태
     */
    @JsonProperty(index = 10)
    @Schema(description = "비회원 상태")
    @Column(length = 100)
    protected boolean status;

}