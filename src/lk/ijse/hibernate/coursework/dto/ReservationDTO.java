package lk.ijse.hibernate.coursework.dto;

import lk.ijse.hibernate.coursework.entity.Room;
import lk.ijse.hibernate.coursework.entity.Student;

import java.sql.Date;

public class ReservationDTO {
    private String resId;
    private Date date;
    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
    private String status;

    public ReservationDTO(String text, StudentDTO studnetDetail, RoomDTO roomDetail, String id) {
    }

    public ReservationDTO(String resId, Date date, StudentDTO studentDTO, RoomDTO roomDTO, String status) {
        this.resId = resId;
        this.date = date;
        this.setStudentDTO (studentDTO);
        this.setRoomDTO (roomDTO);
        this.status = status;
    }


    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "resId='" + resId + '\'' +
                ", date=" + date +
                ", studentDTO=" + studentDTO +
                ", roomDTO=" + roomDTO +
                ", status='" + status + '\'' +
                '}';
    }
}
