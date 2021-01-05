/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateur;
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
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {
    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    @Override
    public long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(u.idUtilisateur) FROM Utilisateur u");
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
    public List<Utilisateur> findByEtat(String etat) {
        Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.etat=:etat");
        query.setParameter("etat", etat);
        return query.getResultList();
    }
    
}
