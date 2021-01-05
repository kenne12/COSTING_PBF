/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Institution;
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
public class InstitutionFacade extends AbstractFacade<Institution> implements InstitutionFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstitutionFacade() {
        super(Institution.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(i.idinstitution) FROM Institution i");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Institution> findAllEtatIsActif() {
        Query query = em.createQuery("SELECT i FROM Institution i WHERE i.etat=:etat");
        query.setParameter("etat", "Actif");
        return query.getResultList();
    }

    @Override
    public List<Institution> findAllRange() {
        Query query = em.createQuery("SELECT i FROM Institution i ORDER BY i.nom");
        return query.getResultList();
    }

}
