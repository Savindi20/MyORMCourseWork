package lk.ijse.hibernate.coursework.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @Column(name = "reservationId", length = 10)
    private String resId;
    @Column(name = "data")
    private Date date;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private Room room;

}

