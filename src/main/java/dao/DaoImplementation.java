package dao;

import entities.Discipline;
import entities.User;
import enums.Disciplines;
import enums.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.List;

public class DaoImplementation implements AllFromOrToDatabase {

    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public List<User> getUsersByRoleName(String roleName) {
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName");
            query.setString("roleName", roleName);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<User> getUsersByDisciplineName(Disciplines disciplineName) {
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("SELECT u FROM User u JOIN u.discipline d WHERE d.name = :disciplineName");
            query.setString("disciplineName", String.valueOf(disciplineName));
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<Discipline> getDisciplineByName(Disciplines disciplineName) {
        Session s = null;
        Transaction t = null;
        List<Discipline> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("SELECT d FROM Discipline d WHERE d.name = :disciplineName");
            query.setString("disciplineName", String.valueOf(disciplineName));
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<User> getUserByUserName(String userName) {
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("SELECT u FROM User u WHERE u.userName = :userName");
            query.setString("userName",userName);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<User> getUsersByTaskStatus(Status taskStatus) {
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("SELECT u FROM User u JOIN u.tasks t WHERE t.status = :taskStatus");
            query.setString("taskStatus", String.valueOf(taskStatus));
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<Discipline> getDisciplineByNumberOfUsers(int nr) {
        Session s = null;
        Transaction t = null;
        List<Discipline> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("SELECT d FROM Discipline d WHERE size(d.members)<= :number");
            query.setInteger("number", nr);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public void deleteUser(User user){
        Session s = null;
        Transaction t = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            s.delete(user);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
    }

}
