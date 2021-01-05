/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.UniteCosting;
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
public class UniteCostingFacade extends AbstractFacade<UniteCosting> implements UniteCostingFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UniteCostingFacade() {
        super(UniteCosting.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(u.iduniteCosting) FROM UniteCosting u");
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
    public List<UniteCosting> findAllRangeByCode() {
        Query query = em.createQuery("SELECT u FROM UniteCosting u ORDER BY u.code");
        return query.getResultList();
    }

}
