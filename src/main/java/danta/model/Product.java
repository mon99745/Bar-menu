package danta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import danta.exception.NotEnoughStockQuantityException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 상품
 */
@Schema(description = "상품")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(indexes = {
        @Index(name = "idx_product_id", columnList = "id", unique = true),
        @Index(name = "idx_product_name", columnList = "name"),
        @Index(name = "idx_product_price", columnList = "price"),
        @Index(name = "idx_product_image", columnList = "image"),
        @Index(name = "idx_product_description", columnList = "description"),
        @Index(name = "idx_product_status", columnList = "status"),
        @Index(name = "idx_product_reg_date", columnList = "regDate"),
        @Index(name = "idx_product_mod_date", columnList = "modDate")})
@org.hibernate.annotations.Table(appliesTo = Product.TABLE_NAME, comment = Product.TABLE_DESC)
public class Product {
    public static final String NAME_SPACE = "Product";
    public static final String TABLE_NAME = "product";
    public static final String TABLE_DESC = "상품";

    /**
     * 상품 ID
     */
    @Id
    @Schema(description = "상품 ID")
    @Column(name = "id", nullable = false)
    protected String id;

    /**
     * 상품 이름
     */
    @JsonProperty(index = 10)
    @Schema(description = "상품 이름")
    @Column(length = 100)
    protected String name;

    /**
     * 상품 가격
     */
    @JsonProperty(index = 10)
    @Schema(description = "상품 가격")
    @Column(length = 100)
    protected Long price;

    /**
     * 상품 이미지
     */
    @JsonProperty(index = 10)
    @Schema(description = "상품 이미지")
    @Column(length = 100)
    protected String image;

    /**
    * 상품 설명
    */
    @JsonProperty(index = 10)
    @Schema(description = "상품 설명")
    @Column(length = 100)
    protected String description;

    /**
     * 상품 재고
     */
    @JsonProperty(index = 10)
    @Schema(description = "상품 가격")
    @Column(length = 100)
    protected int stock;

    public void removeStockQuantity(int orderQuantity) {
        int restStockQuantity = this.stock - orderQuantity;

        if(restStockQuantity < 0)
            throw new NotEnoughStockQuantityException();

        this.stock = restStockQuantity;
    }

    public void addStockQuantity(int quantity) {
        this.stock += quantity;
    }
}
