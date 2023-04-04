package lk.ijse.hibernate.coursework.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserDTO {
    private String userId;
    private String user_name;
    private String password;
}
