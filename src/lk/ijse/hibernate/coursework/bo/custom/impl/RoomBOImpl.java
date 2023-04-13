package lk.ijse.hibernate.coursework.bo.custom.impl;

import lk.ijse.hibernate.coursework.bo.custom.RoomBO;
import lk.ijse.hibernate.coursework.dao.DAOFactory;
import lk.ijse.hibernate.coursework.dao.custom.RoomDAO;
import lk.ijse.hibernate.coursework.dto.RoomDTO;
import lk.ijse.hibernate.coursework.entity.Room;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    private Session session;


    @Override
    public List<RoomDTO> getAllRoom() throws Exception {
        List<RoomDTO>allRoom =new ArrayList<>();
        List<Room> all= roomDAO.getAll();
        for (Room room: all) {
            allRoom.add(new RoomDTO(
                    room.getRoom_type_id(),
                    room.getType(),
                    room.getKey_money(),
                    room.getQty()
            ));
        }return allRoom;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.save(new Room(
                roomDTO.getRoom_type_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(
                roomDTO.getRoom_type_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);
    }

    @Override
    public RoomDTO searchRoom(String id) throws Exception {
        Room room = roomDAO.search(id);
        return new RoomDTO(
                room.getRoom_type_id(),
                room.getType(),
                room.getKey_money(),
                room.getQty());
    }
}
