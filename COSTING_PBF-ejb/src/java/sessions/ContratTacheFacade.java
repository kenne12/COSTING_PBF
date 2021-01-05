/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ContratTache;
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
public class ContratTacheFacade extends AbstractFacade<ContratTache> implements ContratTacheFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratTacheFacade() {
        super(ContratTache.class);
    }

    @Override
    public List<ContratTache> findByIdContrat(Long idContrat) {
        Query query = em.createQuery("SELECT c FROM ContratTache c WHERE c.contratTachePK.idcontrat=:idContrat ORDER BY c.tache.idactivite.code , c.tache.nom");
        query.setParameter("idContrat", idContrat);
        return query.getResultList();
    }

    @Override
    public ContratTache findByIdContratIdtache(Long idContrat, long idTache) {
        Query query = em.createQuery("SELECT c FROM ContratTache c WHERE c.contratTachePK.idcontrat=:idContrat AND c.tache.idtache=:idTache");
        query.setParameter("idContrat", idContrat).setParameter("idTache", idTache);

        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (ContratTache) list.get(0);
        }

        return null;
    }

    @Override
    public List<ContratTache> findByIdContrat(Long idContrat, boolean etatTache) {
        Query query = em.createQuery("SELECT c FROM ContratTache c WHERE c.contratTachePK.idcontrat=:idContrat AND c.etat=:etat ORDER BY c.tache.idactivite.code, c.tache.nom");
        query.setParameter("idContrat", idContrat).setParameter("etat", etatTache);
        return query.getResultList();
    }

    @Override
    public void deleteByIdContrat(Long idContrat) {
        Query query = em.createQuery("DELETE FROM ContratTache c WHERE c.contratTachePK.idcontrat=:idContrat");
        query.setParameter("idContrat", idContrat);
        query.executeUpdate();
    }

}
