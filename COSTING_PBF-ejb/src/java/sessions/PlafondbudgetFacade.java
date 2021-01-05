/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Plafondbudget;
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
public class PlafondbudgetFacade extends AbstractFacade<Plafondbudget> implements PlafondbudgetFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlafondbudgetFacade() {
        super(Plafondbudget.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(p.idplafondbudget) FROM Plafondbudget p");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Plafondbudget> findByIdstructureIdbudget(Long idStructure, Integer idbuget) {
        Query query = em.createQuery("SELECT p FROM Plafondbudget p WHERE p.idstructure.idstructure=:idstructure AND p.idbudget.idbudget=:idbudget ORDER BY p.idmodelecosting.idimputation.code");
        query.setParameter("idstructure", idStructure).setParameter("idbudget", idbuget);
        return query.getResultList();
    }

}
