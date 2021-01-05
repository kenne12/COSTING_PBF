/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Critere;
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
public class CritereFacade extends AbstractFacade<Critere> implements CritereFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CritereFacade() {
        super(Critere.class);
    }
    
    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(c.idcritere) FROM Critere c");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((int) listObj.get(0)) + 1;
            } else {
                return 1 + 1;
            }
        } catch (Exception e) {
            return 1 + 1;
        }
    }

    @Override
    public List<Critere> findAllRangeByCode() {
        Query query = em.createQuery("SELECT c FROM Critere c ORDER BY c.code");
        return query.getResultList();
    }

}
