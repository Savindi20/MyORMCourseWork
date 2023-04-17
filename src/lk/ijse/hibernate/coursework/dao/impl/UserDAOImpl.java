package lk.ijse.hibernate.coursework.dao.impl;

import lk.ijse.hibernate.coursework.dao.custom.UserDAO;
import lk.ijse.hibernate.coursework.entity.User;
import lk.ijse.hibernate.coursework.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class UserDAOImpl implements UserDAO {
    Transaction transaction = null;
    private Session session;

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean save(User object) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean delete(String id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        User user = null;
        user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        return true;

    }

    @Override
    public User search(String id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        User user = null;
        user = session.get(User.class, id);
        transaction.commit();
        return user;
    }

    @Override
    public User getObject(String id) {
        return null;
    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}