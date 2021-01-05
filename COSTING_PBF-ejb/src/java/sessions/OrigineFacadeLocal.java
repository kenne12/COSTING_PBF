/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Origine;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface OrigineFacadeLocal {

    void create(Origine origine);

    void edit(Origine origine);

    void remove(Origine origine);

    Origine find(Object id);

    List<Origine> findAll();

    List<Origine> findRange(int[] range);

    int count();
    
}
