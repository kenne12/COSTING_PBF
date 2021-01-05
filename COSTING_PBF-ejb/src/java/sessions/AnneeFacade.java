/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
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
public class AnneeFacade extends AbstractFacade<Annee> implements AnneeFacadeLocal {
    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnneeFacade() {
        super(Annee.class);
    }
    
    @Override
    public List<Annee> findAllRange() {
        Query query = em.createQuery("SELECT a FROM Annee a ORDER BY a.idannee");
        return (List<Annee>) query.getResultList();
    }

    @Override
    public List<Annee> findByEtat(boolean budget) {
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.budget=:budget ORDER BY a.idannee");
        query.setParameter("budget", budget);
        return (List<Annee>) query.getResultList();
    }
    
}
