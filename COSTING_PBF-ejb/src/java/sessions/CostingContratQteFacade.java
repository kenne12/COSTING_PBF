/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CostingContratQte;
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
public class CostingContratQteFacade extends AbstractFacade<CostingContratQte> implements CostingContratQteFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CostingContratQteFacade() {
        super(CostingContratQte.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(c.idcostingContratQte) FROM CostingContratQte c");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public CostingContratQte findByIdPlafondBudget(long idMoyens, int idUniteCosting) {
        Query query = em.createQuery("SELECT c FROM CostingContratQte c WHERE c.idmoyens.idmoyens=:idMoyens AND c.iduniteCosting.iduniteCosting=:idUniteCosting");
        query.setParameter("idMoyens", idMoyens).setParameter("idUniteCosting", idUniteCosting);
        List<CostingContratQte> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<CostingContratQte> findByIdStructureIdBudget(long idStructure, int idBudget) {
        Query query = em.createQuery("SELECT c FROM CostingContratQte c WHERE c.idmoyens.idtache.idactivite.idstructure.idstructure=:idStructure AND c.idmoyens.idtache.idbudget.idbudget=:idBudget");
        query.setParameter("idStructure", idStructure).setParameter("idBudget", idBudget);
        return query.getResultList();
    }

    @Override
    public List<CostingContratQte> findByIdStructureIdBudgetIdPeriode(long idStructure, int idBudget, int idPeriode) {
        Query query = em.createQuery("SELECT c FROM CostingContratQte c WHERE c.idmoyens.idtache.idactivite.idstructure.idstructure=:idStructure AND c.idmoyens.idtache.idbudget.idbudget=:idBudget AND c.idcontrat.idperiode.idperiode=:idPeriode");
        query.setParameter("idStructure", idStructure).setParameter("idBudget", idBudget).setParameter("idPeriode", idPeriode);
        return query.getResultList();
    }

    @Override
    public void deleteByIdcontratIdMoyen(Long idContrat, long idMoyens) {
        Query query = em.createQuery("DELETE FROM CostingContratQte c WHERE c.idcontrat.idcontrat=:idContrat AND c.idmoyens.idmoyens=:idMoyens");
        query.setParameter("idContrat", idContrat).setParameter("idMoyens", idMoyens);
        query.executeUpdate();
    }

    @Override
    public List<CostingContratQte> findByIdContrat(long idContrat) {
        Query query = em.createQuery("SELECT c FROM CostingContratQte c WHERE c.idcontrat.idcontrat=:idContrat");
        query.setParameter("idContrat", idContrat);
        return query.getResultList();
    }
    
    
    @Override
    public List<CostingContratQte> findByIdContrat(long idContrat , boolean etatTache) {
        Query query = em.createQuery("SELECT c FROM CostingContratQte c WHERE c.idcontrat.idcontrat=:idContrat AND c.etat=:etat");
        query.setParameter("idContrat", idContrat).setParameter("etat", etatTache);
        return query.getResultList();
    }

}
