/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.PrivilegeB;
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
public class PrivilegeBFacade extends AbstractFacade<PrivilegeB> implements PrivilegeBFacadeLocal {

    @PersistenceContext(unitName = "COSTING_PBF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrivilegeBFacade() {
        super(PrivilegeB.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(p.idprivilegeB) FROM PrivilegeB p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<PrivilegeB> findByUser(long idcompte) {
        Query query = em.createQuery("SELECT p FROM PrivilegeB p WHERE p.idCompte.idCompte=:idcompte");
        query.setParameter("idcompte", idcompte);
        return query.getResultList();
    }

    @Override
    public PrivilegeB findByUser(long idcompte, int idmenu) {
        Query query = em.createQuery("SELECT p FROM PrivilegeB p WHERE p.idCompte.idCompte=:idcompte AND p.idmenuB.idmenuB=:idmenu");
        query.setParameter("idcompte", idcompte).setParameter("idmenu", idmenu);
        if (!query.getResultList().isEmpty()) {
            return (PrivilegeB) query.getResultList().get(0);
        }
        return null;
    }

    @Override
    public void delete(int idmenu, long idcompte) {
        try {
            Query query = em.createQuery("DELETE FROM PrivilegeB p WHERE p.idmenuB.idmenuB=:idmenu AND p.idCompte.idCompte=:idcompte");
            query.setParameter("idmenu", idmenu).setParameter("idcompte", idcompte);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
