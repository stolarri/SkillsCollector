package pl.st.skills.model.dao;

import org.hibernate.SessionFactory;
import pl.st.skills.model.entity.Skill;

import java.util.List;

public class SkillDao extends BaseDao {

    public SkillDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Skill get(Long id) {
        return super.produceInTransaction(session -> session.get(Skill.class, id));
    }

    public void save(Skill skill) {
        super.executeInTransaction(session -> session.save(skill));
    }

    public void update(Skill skill) {
        super.executeInTransaction(session -> session.update(skill));
    }

    public void delete(Skill skill) {
        super.executeInTransaction(session -> session.delete(skill));
    }

   /* public Boolean isSkillAvailable(String skill) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(s) FROM Skill s WHERE s.skill = :skill", Long.class)
                        .setParameter("skill", skill)
                        .getSingleResult() <= 0
        );
    } */

    public List<Skill> getAll() {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT s FROM Skill s", Skill.class)
                        .getResultList());
    }

    /* public List<Skill> getAllByName(String name) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT n FROM Skill s WHERE s.name = :name", Skill.class)
                        .setParameter("name", name)
                        .getResultList());
    } */
}
