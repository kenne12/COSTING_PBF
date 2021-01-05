/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Axestrategique;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface AxestrategiqueFacadeLocal {

    void create(Axestrategique axestrategique);

    void edit(Axestrategique axestrategique);

    void remove(Axestrategique axestrategique);

    Axestrategique find(Object id);

    List<Axestrategique> findAll();

    List<Axestrategique> findRange(int[] range);

    int count();
    
}
