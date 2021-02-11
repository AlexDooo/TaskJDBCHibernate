package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.LinkedList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Util util = new Util();
        try {
            SessionFactory sessionFactory = util.getConfiguration().buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Не удалось очистить таблицу");
        }
    }

    @Override
    public void dropUsersTable() {
        Util util = new Util();
        try {
            SessionFactory sessionFactory = util.getConfiguration().buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.createSQLQuery("DROP TABLE hiber_users").executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Не удалось удалить таблицу");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Util util = new Util();

            SessionFactory sessionFactory = util.getConfiguration().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(new User(name, lastName, age));

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Пользователь не добавлен");
        }
    }

    @Override
    public void removeUserById(long id) { // here
        Util util = new Util();
        try {
            SessionFactory sessionFactory = util.getConfiguration().buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            Query query = session.createQuery("DELETE User where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Не удалось удалить объект по ID");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> list;
        try {

            SessionFactory sessionFactory = util.getConfiguration().buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            Query query = session.createQuery("from User");
            list = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Не удалось собрать таблицу в лист");
            return null;
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Util util = new Util();
        try {
            SessionFactory sessionFactory = util.getConfiguration().buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.createQuery("DELETE User ").executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Не удалось очистить таблицу");
        }
    }
}
