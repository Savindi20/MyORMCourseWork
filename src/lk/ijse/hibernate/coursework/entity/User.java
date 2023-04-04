package lk.ijse.hibernate.coursework.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "userId",length = 10)
    private String userId;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "password")
    private String password;

}
