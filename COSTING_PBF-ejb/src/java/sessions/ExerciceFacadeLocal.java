/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Exercice;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ExerciceFacadeLocal {

    void create(Exercice exercice);

    void edit(Exercice exercice);

    void remove(Exercice exercice);

    Exercice find(Object id);

    List<Exercice> findAll();

    List<Exercice> findRange(int[] range);

    int count();
    
}
