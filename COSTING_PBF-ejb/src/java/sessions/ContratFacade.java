/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Contrat;
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
public class ContratFacade extends AbstractFacade<Contrat> implements ContratFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratFacade() {
        super(Contrat.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(c.idcontrat) FROM Contrat c");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public Contrat findByIdtructureIdperiode(Long idStructure, int idBudget, int idPeriode) {
        Query query = em.createQuery("SELECT c FROM Contrat c WHERE c.idstructure.idstructure=:idStructure AND c.idbudget.idbudget=:idBudget AND c.idperiode.idperiode=:idPeriode");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("idBudget", idBudget);
        List<Contrat> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Contrat> findByIdtructureIdBudget(Long idStructure, int idBudget) {
        Query query = em.createQuery("SELECT c FROM Contrat c WHERE c.idstructure.idstructure=:idStructure AND c.idbudget.idbudget=:idBudget ORDER BY c.idperiode.code");
        query.setParameter("idStructure", idStructure).setParameter("idBudget", idBudget);
        return query.getResultList();
    }

    @Override
    public List<Contrat> findByIdInstitutionIdBudget(Long idInstitution, int idBudget) {
        Query query = em.createQuery("SELECT c FROM Contrat c WHERE c.idstructure.idinstitution.idinstitution=:idInstitution AND c.idbudget.idbudget=:idBudget ORDER BY c.idperiode.code");
        query.setParameter("idInstitution", idInstitution).setParameter("idBudget", idBudget);
        return query.getResultList();
    }
    
    @Override
    public List<Contrat> findByIdInstitutionIdBudgetIdPeriode(Long idInstitution, int idBudget , int idPeriode) {
        Query query = em.createQuery("SELECT c FROM Contrat c WHERE c.idstructure.idinstitution.idinstitution=:idInstitution AND c.idbudget.idbudget=:idBudget AND c.idperiode.idperiode=:idPeriode ORDER BY c.idstructure.nom");
        query.setParameter("idInstitution", idInstitution).setParameter("idBudget", idBudget).setParameter("idPeriode", idPeriode);
        return query.getResultList();
    }

}
