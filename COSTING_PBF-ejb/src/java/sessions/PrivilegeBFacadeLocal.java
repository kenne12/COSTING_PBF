/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.PrivilegeB;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface PrivilegeBFacadeLocal {

    void create(PrivilegeB privilegeB);

    void edit(PrivilegeB privilegeB);

    void remove(PrivilegeB privilegeB);

    PrivilegeB find(Object id);

    List<PrivilegeB> findAll();

    List<PrivilegeB> findRange(int[] range);

    int count();

    Long nextVal();

    List<PrivilegeB> findByUser(long idcompte);

    PrivilegeB findByUser(long idcompte, int idmenu);

    void delete(int idmenu, long idcompte);

}
