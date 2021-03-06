/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Niveaupyramide;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class NiveaupyramideFacade extends AbstractFacade<Niveaupyramide> implements NiveaupyramideFacadeLocal {
    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NiveaupyramideFacade() {
        super(Niveaupyramide.class);
    }
    
}
