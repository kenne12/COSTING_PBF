/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Programme;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
@Stateless
public class ProgrammeFacade extends AbstractFacade<Programme> implements ProgrammeFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgrammeFacade() {
        super(Programme.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(p.idprogramme) FROM Programme p");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Programme> findByIdinstitution(long idinstitution) {
        Query query = em.createQuery("SELECT p FROM Programme p WHERE p.idinstitution.idinstitution=:idinstitution ORDER BY p.code");
        query.setParameter("idinstitution", idinstitution);
        return query.getResultList();
    }

}
