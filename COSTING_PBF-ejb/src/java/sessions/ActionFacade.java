/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Action;
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
public class ActionFacade extends AbstractFacade<Action> implements ActionFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActionFacade() {
        super(Action.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(a.idaction) FROM Action a");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Action> findByProgramme(Long idprogramme) {
        Query query = em.createQuery("SELECT a FROM Action a WHERE a.idprogramme.idprogramme=:idprogramme ORDER BY a.code");
        query.setParameter("idprogramme", idprogramme);
        return query.getResultList();
    }

    @Override
    public List<Action> findByInstitution(Long idInstitution) {
        Query query = em.createQuery("SELECT a FROM Action a WHERE a.idprogramme.idinstitution.idinstitution=:idInstitution ORDER BY a.code");
        query.setParameter("idInstitution", idInstitution);
        return query.getResultList();
    }

}
