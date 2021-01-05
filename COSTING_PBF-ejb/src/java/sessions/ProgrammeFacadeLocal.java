/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Programme;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ProgrammeFacadeLocal {

    void create(Programme programme);

    void edit(Programme programme);

    void remove(Programme programme);

    Programme find(Object id);

    List<Programme> findAll();

    List<Programme> findRange(int[] range);

    int count();

    Long nextId();

    List<Programme> findByIdinstitution(long idinstitution);

}
