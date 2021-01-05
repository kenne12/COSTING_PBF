/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Periode;
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
public class PeriodeFacade extends AbstractFacade<Periode> implements PeriodeFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodeFacade() {
        super(Periode.class);
    }

    @Override
    public List<Periode> findAllRange() {
        Query query = em.createQuery("SELECT p FROM Periode p ORDER BY p.code");
        return query.getResultList();
    }

    @Override
    public List<Periode> findByIdFrequence(int idFrequence) {
        Query query = em.createQuery("SELECT p FROM Periode p WHERE p.idfrequence.idfrequence=:idFrequence ORDER BY p.code");
        query.setParameter("idFrequence", idFrequence);
        return query.getResultList();
    }

}
