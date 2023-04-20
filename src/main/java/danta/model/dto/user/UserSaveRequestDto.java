package danta.model.dto.user;



import danta.domain.user.Role;
import danta.domain.user.User;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String username;
    private String password;
    private String email;
//    private String nickname;

    private Role role;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
//                .nickname(nickname)
                .role(Role.USER)
                .build();
    }

    public Map<String, String> getCreateMsg(){
        Map<String, String> createMsg = new HashMap<>();
        createMsg.put("role", String.valueOf(getRole()));
        createMsg.put("username", getUsername());
        createMsg.put("password", getPassword());
        createMsg.put("email", getEmail());
//        createMsg.put("nickname". getNickname());

        return createMsg;
    }

}