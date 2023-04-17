package lk.ijse.hibernate.coursework.dao.impl;

import lk.ijse.hibernate.coursework.dao.custom.RoomDAO;
import lk.ijse.hibernate.coursework.entity.Room;
import lk.ijse.hibernate.coursework.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private Transaction transaction = null;
    private Session session;


    @Override
    public List<Room> getAll() {
        List<Room> rooms = null;
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        rooms = session.createQuery("FROM Room ").list();
        transaction.commit();
        return rooms;
    }

    @Override
    public boolean save(Room entity) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) {
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
        Room room = null;
        room = session.get(Room.class, id);
        session.delete(room);
        transaction.commit();

        return true;
    }

    @Override
    public Room search(String id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        Room room = null;
        room = session.get(Room.class, id);
        transaction.commit();
        return room;
    }

    @Override
    public Room getObject(String id) {
        return null;
    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<String> roomIds() {
        String hql = "SELECT id from Room ";
        Query<String> query=session.createQuery (hql);
        List<String> results = query.list();
        session.close();
        return results;
    }
}

