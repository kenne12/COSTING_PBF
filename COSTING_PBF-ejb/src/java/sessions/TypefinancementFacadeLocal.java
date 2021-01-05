/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typefinancement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypefinancementFacadeLocal {

    void create(Typefinancement typefinancement);

    void edit(Typefinancement typefinancement);

    void remove(Typefinancement typefinancement);

    Typefinancement find(Object id);

    List<Typefinancement> findAll();

    List<Typefinancement> findRange(int[] range);

    int count();
    
}
