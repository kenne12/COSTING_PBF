/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Budget;
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
public class BudgetFacade extends AbstractFacade<Budget> implements BudgetFacadeLocal {
    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BudgetFacade() {
        super(Budget.class);
    }
    
    @Override
    public Integer nextId() {
        Query query = em.createQuery("SELECT MAX(b.idbudget) FROM Budget b");
        Integer resultat = (Integer) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1;
        }
    }

    @Override
    public Budget findByNom(String nom) {
        Query query = em.createQuery("SELECT b FROM Budget b WHERE UPPER(b.annee)=:annee");
        query.setParameter("annee", nom.toUpperCase());
        List<Budget> budgets = query.getResultList();
        if (!budgets.isEmpty()) {
            return budgets.get(0);
        }
        return null;
    }

    @Override
    public List<Budget> findAllRange() {
        Query query = em.createQuery("SELECT b FROM Budget b ORDER BY b.annee");
        return query.getResultList();
    }

    @Override
    public Budget findByAnnee(String annee) {
        Query query = em.createQuery("SELECT b FROM Budget b WHERE b.annee=:annee");
        query.setParameter("annee", annee);
        List<Budget> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
}
