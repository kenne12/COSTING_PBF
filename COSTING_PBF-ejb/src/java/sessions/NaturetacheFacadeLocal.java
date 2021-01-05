/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Naturetache;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface NaturetacheFacadeLocal {

    void create(Naturetache naturetache);

    void edit(Naturetache naturetache);

    void remove(Naturetache naturetache);

    Naturetache find(Object id);

    List<Naturetache> findAll();

    List<Naturetache> findRange(int[] range);

    int count();
    
}
