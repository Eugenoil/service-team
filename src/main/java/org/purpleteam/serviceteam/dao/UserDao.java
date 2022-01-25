package org.purpleteam.serviceteam.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.purpleteam.serviceteam.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {
    public User findById(int id) {
        return DAO.getSessionFactory().openSession().get(User.class, id);
    }

    public User findByName(String name) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("user_name"), name));
            return session.createQuery(cq).getSingleResult();
        } catch (Exception ex) {return null;}
    }

    public User findByTelegramId(Long telegram_id) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("user_telegram_id"), telegram_id));
            return session.createQuery(cq).getSingleResult();
        } catch (Exception ex) {return null;}
    }

    public List<User> findAll() {
        String sql = "SELECT u FROM User u";
        return (List<User>)DAO.getSessionFactory().openSession().createQuery(sql).getResultList();
    }

    public int create(User user) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.save(user);
            trans.commit();
            return 0;
        } catch (Exception ex) {return 1;}
    }

    public int update(User user) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(user);
            trans.commit();
            return 0;
        } catch (Exception ex) {return 1;}
    }

    public int delete(User user) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.delete(user);
            trans.commit();
            return 0;
        } catch (Exception ex) {return 1;}
    }
}
