/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Compte;
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
public class CompteFacade extends AbstractFacade<Compte> implements CompteFacadeLocal {
    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }
    
    @Override
    public long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(c.idCompte) FROM Compte c");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((long) listObj.get(0)) + 1;
            } else {
                return 1 + 1;
            }
        } catch (Exception e) {
            return 1 + 1;
        }
    }

    @Override
    public Compte connexion(Compte compte) {
        try {
            Query query = em.createQuery("SELECT c FROM Compte c WHERE c.login=:login AND c.mdp=:mdp");
            query.setParameter("login", compte.getLogin()).setParameter("mdp", compte.getMdp());
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                Compte cpte = (Compte) listObj.get(0);
                em.refresh(cpte);
                return cpte;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Compte> findByEtat(String etat) {
        Query query = em.createQuery("SELECT c FROM Compte c WHERE c.etat=:etat");
        query.setParameter("etat", etat);
        return query.getResultList();
    }

    @Override
    public Compte findByIdutilisateur(long idutilisateur) {
        Query query = em.createQuery("SELECT c FROM Compte c WHERE c.idUtilisateur.idUtilisateur=:idutilisateur");
        query.setParameter("idutilisateur", idutilisateur);
        List<Compte> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
}
