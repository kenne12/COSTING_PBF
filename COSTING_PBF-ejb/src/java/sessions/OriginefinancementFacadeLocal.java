/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Originefinancement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface OriginefinancementFacadeLocal {

    void create(Originefinancement originefinancement);

    void edit(Originefinancement originefinancement);

    void remove(Originefinancement originefinancement);

    Originefinancement find(Object id);

    List<Originefinancement> findAll();

    List<Originefinancement> findRange(int[] range);

    int count();
    
}
