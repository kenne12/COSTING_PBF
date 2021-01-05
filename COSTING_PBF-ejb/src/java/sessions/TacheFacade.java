/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Tache;
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
public class TacheFacade extends AbstractFacade<Tache> implements TacheFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TacheFacade() {
        super(Tache.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(t.idtache) FROM Tache t");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Tache> findByStructureAnne(Long idstructure, Long idannee) {
        Query query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idstructure=:idstructure AND t.idannee.idannee=:idannee");
        query.setParameter("structure", idstructure).setParameter("annee", idannee);
        return query.getResultList();
    }

    @Override
    public List<Tache> findByIdStructureIdAnneIdPeriode(Long idstructure, Long idannee, int idPeriode) {
        Query query = null;
        if (idPeriode == 1) {
            query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idstructure=:idstructure AND t.idannee.idannee=:idannee AND (t.m1=true OR t.m2=true OR t.m3=true) ORDER BY t.nom");
        } else if (idPeriode == 2) {
            query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idstructure=:idstructure AND t.idannee.idannee=:idannee AND (t.m4=true OR t.m5=true OR t.m6=true) ORDER BY t.nom");
        } else if (idPeriode == 3) {
            query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idstructure=:idstructure AND t.idannee.idannee=:idannee AND (t.m7=true OR t.m8=true OR t.m9=true) ORDER BY t.nom");
        } else if (idPeriode == 4) {
            query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idstructure=:idstructure AND t.idannee.idannee=:idannee AND (t.m10=true OR t.m11=true OR t.m12=true) ORDER BY t.nom");
        } else if (idPeriode == 5) {
            query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idstructure=:idstructure AND t.idannee.idannee=:idannee AND (t.m1=true OR t.m2=true OR t.m3=true OR t.m4=true OR t.m5=true OR t.m6=true) ORDER BY t.nom");
        } else if (idPeriode == 5) {
            query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idstructure=:idstructure AND t.idannee.idannee=:idannee AND (t.m7=true OR t.m8=true OR t.m9=true OR t.m10=true OR t.m11=true OR t.m12=true) ORDER BY t.nom");
        } else {
            query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idstructure=:idstructure AND t.idannee.idannee=:idannee AND t.idevaluationstructure.idperiode.idperiode=:idPeriode ORDER BY t.nom");
            query.setParameter("idPeriode", idPeriode);
        }

        query.setParameter("idstructure", idstructure).setParameter("idannee", idannee);
        return query.getResultList();
    }

    @Override
    public List<Tache> findByIdInstitution(Long idInstitution, Long idannee) {
        Query query = em.createQuery("SELECT t FROM Tache t WHERE t.idstructure.idinstitution.idinstitution=:idInstitution AND t.idannee.idannee=:idannee ORDER BY t.idstructure.nom , t.idevaluationstructure.idperiode");
        query.setParameter("idInstitution", idInstitution).setParameter("idannee", idannee);
        return query.getResultList();
    }

}
