/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ContratMoyens;
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
public class ContratMoyensFacade extends AbstractFacade<ContratMoyens> implements ContratMoyensFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratMoyensFacade() {
        super(ContratMoyens.class);
    }

    @Override
    public List<ContratMoyens> findByIdContrat(Long idContrat) {
        Query query = em.createQuery("SELECT c FROM ContratMoyens c WHERE c.contratMoyensPK.idcontrat=:idContrat ORDER BY c.moyens.idtache.nom");
        query.setParameter("idContrat", idContrat);
        return query.getResultList();
    }

    @Override
    public List<ContratMoyens> findByIdContrat(Long idContrat, boolean etatTache) {
        Query query = em.createQuery("SELECT c FROM ContratMoyens c WHERE c.contratMoyensPK.idcontrat=:idContrat AND c.etat=:etat ORDER BY c.moyens.idtache.nom");
        query.setParameter("idContrat", idContrat).setParameter("etat", etatTache);
        return query.getResultList();
    }

    @Override
    public List<ContratMoyens> findByIdPeriodeFb(int idPeriode, long idInstitution, int idBudget) {
        Query query = em.createQuery("SELECT c FROM ContratMoyens c WHERE c.moyens.idtache.idstructure.idinstitution.idinstitution=:idInstitution AND c.contrat.idperiode.idperiode=:idPeriode AND c.contrat.idbudget.idbudget=:idBudget ORDER BY c.moyens.idtache.idbailleurfond.nom");
        query.setParameter("idInstitution", idInstitution).setParameter("idPeriode", idPeriode).setParameter("idBudget", idBudget);
        return query.getResultList();
    }

    @Override
    public List<ContratMoyens> findByIdPeriodeFbEtatTache(int idPeriode, long idInstitution, int idBudget, boolean etatTache) {
        Query query = em.createQuery("SELECT c FROM ContratMoyens c WHERE c.moyens.idtache.idstructure.idinstitution.idinstitution=:idInstitution AND c.contrat.idperiode.idperiode=:idPeriode AND c.contrat.idbudget.idbudget=:idBudget AND c.etat=:etat ORDER BY c.moyens.idtache.idbailleurfond.nom");
        query.setParameter("idInstitution", idInstitution).setParameter("idPeriode", idPeriode).setParameter("idBudget", idBudget).setParameter("etat", etatTache);
        return query.getResultList();
    }

    @Override
    public List<ContratMoyens> findByIdPeriodeFa(int idPeriode, long idInstitution, int idBudget) {
        Query query = em.createQuery("SELECT c FROM ContratMoyens c WHERE c.moyens.idtache.idstructure.idinstitution.idinstitution=:idInstitution AND c.contrat.idperiode.idperiode=:idPeriode AND c.contrat.idbudget.idbudget=:idBudget ORDER BY c.moyens.idtache.idactivite.idaction.code");
        query.setParameter("idInstitution", idInstitution).setParameter("idPeriode", idPeriode).setParameter("idBudget", idBudget);
        return query.getResultList();
    }

    @Override
    public List<ContratMoyens> findByIdPeriodeFaEtatTache(int idPeriode, long idInstitution, int idBudget, boolean etatTache) {
        Query query = em.createQuery("SELECT c FROM ContratMoyens c WHERE c.moyens.idtache.idstructure.idinstitution.idinstitution=:idInstitution AND c.contrat.idperiode.idperiode=:idPeriode AND c.contrat.idbudget.idbudget=:idBudget AND c.etat=:etat ORDER BY c.moyens.idtache.idactivite.idaction.idprogramme.code,c.moyens.idtache.idactivite.idaction.code");
        query.setParameter("idInstitution", idInstitution).setParameter("idPeriode", idPeriode).setParameter("idBudget", idBudget).setParameter("etat", etatTache);
        return query.getResultList();
    }

    @Override
    public void deleteByIdContrat(Long idContrat) {
        Query query = em.createQuery("DELETE FROM ContratMoyens c WHERE c.contratMoyensPK.idcontrat=:idContrat");
        query.setParameter("idContrat", idContrat);
        query.executeUpdate();
    }

    @Override
    public List<ContratMoyens> findByIdTache(Long idTache) {
        Query query = em.createQuery("SELECT c FROM ContratMoyens c WHERE c.moyens.idtache.idtache=:idTache");
        query.setParameter("idTache", idTache);
        return query.getResultList();
    }

    @Override
    public List<ContratMoyens> findByIdPeriodeIdActivite(int idPeriode, long idInstitution, int idBudget, long idActivite, boolean etatTache) {
        Query query = em.createQuery("SELECT c FROM ContratMoyens c WHERE c.moyens.idtache.idstructure.idinstitution.idinstitution=:idInstitution AND c.contrat.idperiode.idperiode=:idPeriode AND c.contrat.idbudget.idbudget=:idBudget AND c.moyens.idtache.idactivite.idactivite=:idActivite AND c.etat=:etat ORDER BY c.moyens.idtache.idbailleurfond.nom");
        query.setParameter("idInstitution", idInstitution).setParameter("idPeriode", idPeriode).setParameter("idBudget", idBudget).setParameter("idActivite", idActivite).setParameter("etat", etatTache);
        return query.getResultList();
    }

}
