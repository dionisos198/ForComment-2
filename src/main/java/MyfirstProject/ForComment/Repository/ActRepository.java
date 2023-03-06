package MyfirstProject.ForComment.Repository;

import MyfirstProject.ForComment.Domain.Act;
import MyfirstProject.ForComment.Domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ActRepository {
    @PersistenceContext
    EntityManager em;
    public Long save(Act act){
        if(act.getId()==null){
            em.persist(act);
        }
        else{
            em.merge(act);
        }

        return act.getId();
    }
    public Act findOne(Long id){
        return em.find(Act.class,id);
    }
    public List<Act> findAll(){
        return em.createQuery("select a from Act a",Act.class).getResultList();
    }
    public List<Act> findByName(String name){
        return em.createQuery("select a from Act a where a.name=:name",Act.class)
                .setParameter("name",name).getResultList();
    }
}
