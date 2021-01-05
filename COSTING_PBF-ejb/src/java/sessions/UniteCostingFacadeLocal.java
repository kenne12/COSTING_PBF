/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.UniteCosting;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface UniteCostingFacadeLocal {

    void create(UniteCosting uniteCosting);

    void edit(UniteCosting uniteCosting);

    void remove(UniteCosting uniteCosting);

    UniteCosting find(Object id);

    List<UniteCosting> findAll();

    List<UniteCosting> findRange(int[] range);

    int count();

    int nextId();

    List<UniteCosting> findAllRangeByCode();

}
