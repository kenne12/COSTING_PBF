/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sousrubrique;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SousrubriqueFacadeLocal {

    void create(Sousrubrique sousrubrique);

    void edit(Sousrubrique sousrubrique);

    void remove(Sousrubrique sousrubrique);

    Sousrubrique find(Object id);

    List<Sousrubrique> findAll();

    List<Sousrubrique> findRange(int[] range);

    int count();
    
}
