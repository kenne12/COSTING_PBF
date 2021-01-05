/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubrique;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface RubriqueFacadeLocal {

    void create(Rubrique rubrique);

    void edit(Rubrique rubrique);

    void remove(Rubrique rubrique);

    Rubrique find(Object id);

    List<Rubrique> findAll();

    List<Rubrique> findRange(int[] range);

    int count();
    
}
