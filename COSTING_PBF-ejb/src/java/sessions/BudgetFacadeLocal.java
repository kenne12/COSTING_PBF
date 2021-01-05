/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Budget;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface BudgetFacadeLocal {

    void create(Budget budget);

    void edit(Budget budget);

    void remove(Budget budget);

    Budget find(Object id);

    List<Budget> findAll();

    List<Budget> findRange(int[] range);

    int count();

    Integer nextId();

    Budget findByNom(String nom);

    List<Budget> findAllRange();

    Budget findByAnnee(String annee);

}
