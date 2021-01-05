/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Imputation;
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
public class ImputationFacade extends AbstractFacade<Imputation> implements ImputationFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImputationFacade() {
        super(Imputation.class);
    }

    @Override
    public long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(r.idimputation) FROM Imputation r");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Long) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public List<Imputation> find(int rubrique, int sousrubrique) {
        Query query = em.createQuery("SELECT r FROM Imputation r WHERE r.idsousrubrique.idrubrique.idrubrique=:rubrique AND r.idsousrubrique.idsousrubrique=:sousrubrique ORDER BY r.code , r.nicename");
        query.setParameter("rubrique", rubrique).setParameter("sousrubrique", sousrubrique);
        return query.getResultList();

    }

    @Override
    public List<Imputation> findAllOrder() {
        Query query = em.createQuery("SELECT r FROM Imputation r ORDER BY r.code , r.nicename");
        return query.getResultList();
    }

}
