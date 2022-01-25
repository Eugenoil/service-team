package org.purpleteam.serviceteam.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.purpleteam.serviceteam.entity.Role;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleDao {
    public Role findById(int id) {
        return DAO.getSessionFactory().openSession().get(Role.class, id);
    }

    public List<Role> findAll() {
        try (Session session = DAO.getSessionFactory().openSession()) {
            CriteriaQuery<Role> cq = session.getCriteriaBuilder().createQuery(Role.class);
            Root<Role> root = cq.from(Role.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public int create(Role role) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            if (findById(role.getRole_id()) == null) {
                Transaction trans = session.beginTransaction();
                session.save(role);
                trans.commit();
                return 0;
            } else {
                return 1;
            }
        }
    }

    public int update(Role role) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(role);
            trans.commit();
            return 0;
        } catch (Exception ex) {
            return 1;
        }
    }

    public int delete(Role role) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.delete(role);
            trans.commit();
            return 0;
        } catch (Exception ex) {
            return 1;
        }
    }
}
