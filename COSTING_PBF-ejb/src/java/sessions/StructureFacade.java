/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Structure;
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
public class StructureFacade extends AbstractFacade<Structure> implements StructureFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StructureFacade() {
        super(Structure.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(s.idstructure) FROM Structure s");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<Structure> findAllRange() {
        Query query = em.createQuery("SELECT s FROM Structure s ORDER BY s.nom");
        return (List<Structure>) query.getResultList();
    }

    @Override
    public List<Structure> findAllRangeEtatInstitutionIsActif() {
        Query query = em.createQuery("SELECT s FROM Structure s WHERE s.idinstitution.etat=:etat ORDER BY s.nom");
        query.setParameter("etat", "Actif");
        return (List<Structure>) query.getResultList();
    }

    @Override
    public List<Structure> findByIdinstitution(long idinstitution) {
        Query query = em.createQuery("SELECT s FROM Structure s WHERE s.idinstitution.idinstitution=:idinstitution");
        query.setParameter("idinstitution", idinstitution);
        return query.getResultList();
    }

}
