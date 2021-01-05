/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Moyens;
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
public class MoyensFacade extends AbstractFacade<Moyens> implements MoyensFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MoyensFacade() {
        super(Moyens.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(m.idmoyens) FROM Moyens m");
        Long resultat = (Long) query.getSingleResult();
        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1l;
        }
    }

    @Override
    public List<Moyens> findByStructureAnnee(Long idstructure, Long idannee) {
        Query query = em.createQuery("SELECT m FROM Moyens m WHERE m.idtache.idstructure.idstructure=:idstructure AND m.idtache.idannee.idannee=:idannee ORDER BY m.idimputation.code");
        query.setParameter("idstructure", idstructure).setParameter("idannee", idannee);
        return query.getResultList();
    }

    @Override
    public List<Moyens> findByTache(Long idtache) {
        Query query = em.createQuery("SELECT m FROM Moyens m WHERE m.idtache.idtache=:idtache ORDER BY m.idimputation.code");
        query.setParameter("idtache", idtache);
        return query.getResultList();
    }

    @Override
    public List<Moyens> findByStructureAnneeIdimputation(Long idstructure, Long idannee, Long idimputation) {
        Query query = em.createQuery("SELECT m FROM Moyens m WHERE m.idtache.idstructure.idstructure=:idstructure AND m.idtache.idannee.idannee=:idannee AND m.idimputation.idimputation=:idimputation");
        query.setParameter("idstructure", idstructure).setParameter("idannee", idannee).setParameter("idimputation", idimputation);
        return query.getResultList();
    }

    @Override
    public void deleteByIdTache(Long idtache) {
        Query query = em.createQuery("DELETE FROM Moyens m WHERE m.idtache.idtache=:idtache");
        query.setParameter("idtache", idtache);
        query.executeUpdate();
    }

}
