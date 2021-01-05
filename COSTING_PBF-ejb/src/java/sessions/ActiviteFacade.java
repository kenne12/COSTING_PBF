/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
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
public class ActiviteFacade extends AbstractFacade<Activite> implements ActiviteFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteFacade() {
        super(Activite.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(a.idactivite) FROM Activite a");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Activite> findByAction(Long idaction) {
        Query query = em.createQuery("SELECT a FROM Activite a WHERE a.idaction.idaction=:idaction ORDER BY a.code");
        query.setParameter("idaction", idaction);
        return query.getResultList();
    }

    @Override
    public List<Activite> findByActionStructure(Long idaction, long idStructure) {
        Query query = em.createQuery("SELECT a FROM Activite a WHERE a.idaction.idaction=:idaction AND a.idstructure.idstructure=:idStructure ORDER BY a.code");
        query.setParameter("idaction", idaction).setParameter("idStructure", idStructure);
        return query.getResultList();
    }

    @Override
    public List<Activite> findByIdInstitution(Long idInstitution) {
        Query query = em.createQuery("SELECT a FROM Activite a WHERE a.idstructure.idinstitution.idinstitution=:idInstitution ORDER BY a.code");
        query.setParameter("idInstitution", idInstitution);
        return query.getResultList();
    }

}
