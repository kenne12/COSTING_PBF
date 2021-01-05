/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Pyramide;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface PyramideFacadeLocal {

    void create(Pyramide pyramide);

    void edit(Pyramide pyramide);

    void remove(Pyramide pyramide);

    Pyramide find(Object id);

    List<Pyramide> findAll();

    List<Pyramide> findRange(int[] range);

    int count();
    
}
