/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Modelecosting;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ModelecostingFacadeLocal {

    void create(Modelecosting modelecosting);

    void edit(Modelecosting modelecosting);

    void remove(Modelecosting modelecosting);

    Modelecosting find(Object id);

    List<Modelecosting> findAll();

    List<Modelecosting> findRange(int[] range);

    int count();

    int nextId();

    List<Modelecosting> findAllRange();

}
