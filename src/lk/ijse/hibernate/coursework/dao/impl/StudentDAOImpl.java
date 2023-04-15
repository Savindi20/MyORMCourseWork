package lk.ijse.hibernate.coursework.dao.impl;


import lk.ijse.hibernate.coursework.dao.custom.StudentDAO;
import lk.ijse.hibernate.coursework.entity.Student;
import lk.ijse.hibernate.coursework.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class StudentDAOImpl implements StudentDAO {
    private Transaction transaction = null;
    private Session session;


    @Override
    public List<Student> getAll() {
        List<Student> student = null;
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        student = session.createQuery("FROM Student ").list();
        transaction.commit();
        return student;
    }

    @Override
    public boolean save(Student entity) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) {
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
        Student student = null;
        student = session.get(Student.class, id);
        session.delete(student);
        transaction.commit();

        return true;
    }

    @Override
    public Student search(String id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        Student student = null;
        student = session.get(Student.class, id);
        transaction.commit();
        return student;
    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<String> getStIds() {
        String hql = "SELECT id from Student ";
        Query<String> query=session.createQuery (hql);
        List<String> results = query.list();
        session.close();
        return results;
    }
}
