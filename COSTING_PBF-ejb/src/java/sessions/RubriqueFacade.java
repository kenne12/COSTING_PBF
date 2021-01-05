/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubrique;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class RubriqueFacade extends AbstractFacade<Rubrique> implements RubriqueFacadeLocal {
    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriqueFacade() {
        super(Rubrique.class);
    }
    
}
