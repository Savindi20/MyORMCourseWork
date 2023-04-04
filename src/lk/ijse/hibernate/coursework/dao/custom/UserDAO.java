package lk.ijse.hibernate.coursework.dao.custom;

import lk.ijse.hibernate.coursework.dao.CrudDAO;
import lk.ijse.hibernate.coursework.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {
    void setSession(Session session);
    List<String> geIds() throws Exception;
}
