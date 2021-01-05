/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationstructure;
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
public class EvaluationstructureFacade extends AbstractFacade<Evaluationstructure> implements EvaluationstructureFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationstructureFacade() {
        super(Evaluationstructure.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(e.idevaluationstructure) FROM Evaluationstructure e");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Evaluationstructure> findByIdStructure(Long idStructure, int idBudget, int idPeriode) {
        Query query = em.createQuery("SELECT e FROM Evaluationstructure e WHERE e.idstructure.idstructure=:idStructure AND e.idbudget.idbudget=:idBudget AND e.idperiode.idperiode=:idPeriode ORDER BY e.iddetailsc.idsouscritere.code");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("idBudget", idBudget);
        return query.getResultList();
    }

}
