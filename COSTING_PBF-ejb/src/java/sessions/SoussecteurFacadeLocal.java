/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Soussecteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SoussecteurFacadeLocal {

    void create(Soussecteur soussecteur);

    void edit(Soussecteur soussecteur);

    void remove(Soussecteur soussecteur);

    Soussecteur find(Object id);

    List<Soussecteur> findAll();

    List<Soussecteur> findRange(int[] range);

    int count();
    
}
