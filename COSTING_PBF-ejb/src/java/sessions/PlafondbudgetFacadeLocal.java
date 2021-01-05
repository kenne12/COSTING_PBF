/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Plafondbudget;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface PlafondbudgetFacadeLocal {

    void create(Plafondbudget plafondbudget);

    void edit(Plafondbudget plafondbudget);

    void remove(Plafondbudget plafondbudget);

    Plafondbudget find(Object id);

    List<Plafondbudget> findAll();

    List<Plafondbudget> findRange(int[] range);

    int count();

    Long nextId();
    
    List<Plafondbudget> findByIdstructureIdbudget(Long idStructure , Integer idbuget);

}
