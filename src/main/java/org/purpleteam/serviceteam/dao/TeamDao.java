package org.purpleteam.serviceteam.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.purpleteam.serviceteam.entity.Team;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeamDao {
    public Team findById(int id) {
        return DAO.getSessionFactory().openSession().get(Team.class, id);
    }

    public Team findByName(String name) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Team> cq = cb.createQuery(Team.class);
            Root<Team> root = cq.from(Team.class);
            cq.select(root).where(cb.equal(root.get("team_name"), name));
            return  session.createQuery(cq).getSingleResult();
        } catch (Exception ex) {return null;}
    }

    public Team findByLead(Long leadId) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Team> cq = cb.createQuery(Team.class);
            Root<Team> root = cq.from(Team.class);
            cq.select(root).where(cb.equal(root.get("team_lead"), leadId));
            return  session.createQuery(cq).getSingleResult();
        } catch (Exception ex) {return null;}
    }
    
    public List<Team> findAll() {
        try (Session session = DAO.getSessionFactory().openSession()) {
            CriteriaQuery<Team> cq = session.getCriteriaBuilder().createQuery(Team.class);
            Root<Team> root = cq.from(Team.class);
            cq.select(root);
            Query<Team> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception ex) {return null;}
    }

    public int create(Team team) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            if (findById(team.getTeam_id()) == null) {
                Transaction trans = session.beginTransaction();
                session.save(team);
                trans.commit();
                return 0;
            } else {return 1;}
        }
    }

    public int update(Team team) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(team);
            trans.commit();
            return 0;
        } catch (Exception ex) {return 1;}
    }

    public int delete(Team team) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.delete(team);
            trans.commit();
            return 0;
        } catch (Exception ex) {return 1;}
    }
}
