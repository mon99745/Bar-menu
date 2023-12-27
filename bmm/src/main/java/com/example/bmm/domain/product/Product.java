package com.example.bmm.domain.product;

import com.example.bmc.exception.BmmError;
import com.example.bmc.exception.BmmException;
import com.example.bmm.domain.AbstractModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 상품 정보 ENTITY
 */
@Schema(description = "상품")
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Embeddable
@DynamicInsert
@DynamicUpdate
@Component
@Table(name = "ProductInfo", indexes = {
        @Index(name = "idx_product_id", columnList = "product_id", unique = true),
        @Index(name = "idx_product_category", columnList = "category"),
        @Index(name = "idx_product_name", columnList = "name"),
        @Index(name = "idx_product_price", columnList = "price"),
        @Index(name = "idx_product_status", columnList = "status"),
        @Index(name = "idx_product_image", columnList = "image"),
        @Index(name = "idx_product_description", columnList = "description"),
        @Index(name = "idx_product_stock", columnList = "stock"),
        @Index(name = "idx_product_reg_date", columnList = "regDate"),
        @Index(name = "idx_product_mod_date", columnList = "modDate")})
public class Product extends AbstractModel {

    /**
     * 상품 ID
     */
    @Id
    @Schema(description = "상품 ID")
    @Column(name = "product_id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * 상품 카테고리
     * TODO : ENUM 으로 변경
     */
    @JsonProperty(index = 10)
    @Schema(description = "상품 카테고리")
    @Column(length = 100)
    protected String category;

    /**
     * 상품 이름
     */
    @JsonProperty(index = 20)
    @Schema(description = "상품 이름")
    @Column(length = 100)
    protected String name;

    /**
     * 상품 가격
     */
    @JsonProperty(index = 30)
    @Schema(description = "상품 가격")
    @Column(length = 100)
    protected int price;

    /**
     * 상품 상태
     */
    @JsonProperty(index = 40)
    @Schema(description = "상품 상태")
    @Column(length = 100)
    protected String status;

    /**
     * 상품 이미지
     * TODO 상품이미지를 경로로 변경
     */
    @JsonProperty(index = 50)
    @Schema(description = "상품 이미지")
    @Column(length = 100)
    protected String image;

    /**
    * 상품 설명
    */
    @JsonProperty(index = 60)
    @Schema(description = "상품 설명")
    @Column(length = 100)
    protected String description;

    /**
     * 상품 재고
     */
    @JsonProperty(index = 70)
    @Schema(description = "상품 재고")
    @Column(length = 100)
    protected int stock;

    public void removeStockQuantity(int orderQuantity) {
        int restStockQuantity = this.stock - orderQuantity;

        if(restStockQuantity < 0){
            throw new BmmException(BmmError.BMM_EMPTY_STOCK_QUANTITY, null);
        }

        this.stock = restStockQuantity;
    }

    public void addStockQuantity(int quantity) {
        this.stock += quantity;
    }
}
