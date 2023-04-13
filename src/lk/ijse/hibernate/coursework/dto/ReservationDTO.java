package lk.ijse.hibernate.coursework.dto;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDTO {
    private String resId;
    private Date date;
    private String student_id;
    private String room_type_id;
    private String status;
    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
}
