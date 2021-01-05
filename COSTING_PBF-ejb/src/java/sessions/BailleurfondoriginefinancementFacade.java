/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Bailleurfondoriginefinancement;
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
public class BailleurfondoriginefinancementFacade extends AbstractFacade<Bailleurfondoriginefinancement> implements BailleurfondoriginefinancementFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BailleurfondoriginefinancementFacade() {
        super(Bailleurfondoriginefinancement.class);
    }

    @Override
    public List<Bailleurfondoriginefinancement> findByIdBailleurfond(long idBailleurfond) {
        Query query = em.createQuery("SELECT b FROM Bailleurfondoriginefinancement b WHERE b.idbailleurfond.idbailleurfond=:idBailleurfond ORDER BY b.idoriginefinancement.nom");
        query.setParameter("idBailleurfond", idBailleurfond);
        return query.getResultList();
    }

}
