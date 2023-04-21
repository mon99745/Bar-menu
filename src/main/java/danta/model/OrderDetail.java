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
 * 주문 상세
 */
@Schema(description = "주문 상세")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(indexes = {
        @Index(name = "idx_orderDetail_id", columnList = "id", unique = true),
        @Index(name = "idx_orderDetail_name", columnList = "name"),
        @Index(name = "idx_orderDetail_price", columnList = "price"),
        @Index(name = "idx_orderDetail_image", columnList = "image"),
        @Index(name = "idx_orderDetail_description", columnList = "description"),
        @Index(name = "idx_orderDetail_status", columnList = "status"),
        @Index(name = "idx_orderDetail_reg_date", columnList = "regDate"),
        @Index(name = "idx_orderDetail_mod_date", columnList = "modDate")})
@org.hibernate.annotations.Table(appliesTo = OrderDetail.TABLE_NAME, comment = OrderDetail.TABLE_DESC)
public class OrderDetail {
    public static final String NAME_SPACE = "OrderDetail";
    public static final String TABLE_NAME = "orderDetail";
    public static final String TABLE_DESC = "주문 상세";

    /**
     * 주문 상세 ID
     */
    @Id
    @Schema(description = "주문 ID")
    @Column(name = "id", nullable = false)
    protected String id;


}