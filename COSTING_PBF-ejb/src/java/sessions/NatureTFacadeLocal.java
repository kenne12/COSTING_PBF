/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.NatureT;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface NatureTFacadeLocal {

    void create(NatureT natureT);

    void edit(NatureT natureT);

    void remove(NatureT natureT);

    NatureT find(Object id);

    List<NatureT> findAll();

    List<NatureT> findRange(int[] range);

    int count();
    
}
