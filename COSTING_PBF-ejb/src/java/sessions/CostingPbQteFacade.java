/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CostingPbQte;
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
public class CostingPbQteFacade extends AbstractFacade<CostingPbQte> implements CostingPbQteFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CostingPbQteFacade() {
        super(CostingPbQte.class);
    }

    @Override
    public CostingPbQte findByIdPlafondBudget(long idPlafondBudget, int idUniteCosting) {
        Query query = em.createQuery("SELECT c FROM CostingPbQte c WHERE c.plafondbudget.idplafondbudget=:idPlafondBudget AND c.uniteCosting.iduniteCosting=:idUniteCosting");
        query.setParameter("idPlafondBudget", idPlafondBudget).setParameter("idUniteCosting", idUniteCosting);
        List<CostingPbQte> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<CostingPbQte> findByIdStructureIdBudget(long idStructure, int idBudget) {
        Query query = em.createQuery("SELECT c FROM CostingPbQte c WHERE c.plafondbudget.idstructure.idstructure=:idStructure AND c.plafondbudget.idbudget.idbudget=:idBudget");
        query.setParameter("idStructure", idStructure).setParameter("idBudget", idBudget);
        return query.getResultList();
    }

}
