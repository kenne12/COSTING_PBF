/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Imputation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ImputationFacadeLocal {

    void create(Imputation imputation);

    void edit(Imputation imputation);

    void remove(Imputation imputation);

    Imputation find(Object id);

    List<Imputation> findAll();

    List<Imputation> findRange(int[] range);

    int count();

    long nextId();

    List<Imputation> find(int rubrique, int sousrubrique);

    List<Imputation> findAllOrder();

}
