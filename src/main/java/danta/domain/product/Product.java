package danta.domain.product;

import danta.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder // 어느 필드에 어떤 값을 채워야하는지 명확하게 알 수 있기 때문에
@AllArgsConstructor
@NoArgsConstructor // Lombok - 빈 생성자를 만듬
@Entity //JPA로 관리되는 엔티티 객체 (테이블)
public class Product extends BaseTimeEntity {

    @Id // 테이블의 Primary Key(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // 상품번호 Int ->Long

    @Column(nullable = false, length = 20, unique = true)
    private String productName; // 상품이름

    @Column(nullable = false, length = 100)
    private int productPrice; // 상품가격

    @Column(nullable = false, length = 50)
    private String productDesc; // 상품 상세정보

    @Column(nullable = false, length = 20)
    private String productUrl; // 상품이미지 경로

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] productPhoto; // 상품이미지파일

    /**
     * 상품수정 메소드
     */
    public void update(String password, String nickname) {
//        this.password = password;
//        this.nickname = nickname;
    }
}
