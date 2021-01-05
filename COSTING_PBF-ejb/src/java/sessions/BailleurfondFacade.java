/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Bailleurfond;
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
public class BailleurfondFacade extends AbstractFacade<Bailleurfond> implements BailleurfondFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BailleurfondFacade() {
        super(Bailleurfond.class);
    }

    @Override
    public long nextId() {
        Query query = em.createQuery("SELECT MAX(b.idbailleurfond) FROM Bailleurfond b");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Bailleurfond> findAllRange() {
        Query query = em.createQuery("SELECT b FROM Bailleurfond b ORDER BY b.nom");
        return query.getResultList();
    }

}
