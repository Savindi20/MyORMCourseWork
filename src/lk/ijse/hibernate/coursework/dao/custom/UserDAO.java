package lk.ijse.hibernate.coursework.dao.custom;

import lk.ijse.hibernate.coursework.dao.CrudDAO;
import lk.ijse.hibernate.coursework.entity.User;
import org.hibernate.Session;

public interface UserDAO extends CrudDAO<User,String> {
    void setSession(Session session);
}
