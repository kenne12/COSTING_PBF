/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Frequence;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface FrequenceFacadeLocal {

    void create(Frequence frequence);

    void edit(Frequence frequence);

    void remove(Frequence frequence);

    Frequence find(Object id);

    List<Frequence> findAll();

    List<Frequence> findRange(int[] range);

    int count();
    
}
