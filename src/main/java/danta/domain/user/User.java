package danta.domain.user;

import danta.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder // 어느 필드에 어떤 값을 채워야하는지 명확하게 알 수 있기 때문에
@AllArgsConstructor
@NoArgsConstructor // Lombok - 빈 생성자를 만듬
@Entity //JPA로 관리되는 엔티티 객체 (테이블)
public class User extends BaseTimeEntity {

    @Id // 테이블의 Primary Key(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //sequence, auto_increment

    @Column(nullable = false, length = 20, unique = true)
    private String username; //아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String nickname; //닉네임

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * 비밀번호 암호화 메소드
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 권한 메소드
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

    /**
     * 회원수정 메소드
     */
    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }
}
