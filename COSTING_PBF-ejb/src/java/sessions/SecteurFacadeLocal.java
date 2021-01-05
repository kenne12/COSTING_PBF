/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Secteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SecteurFacadeLocal {

    void create(Secteur secteur);

    void edit(Secteur secteur);

    void remove(Secteur secteur);

    Secteur find(Object id);

    List<Secteur> findAll();

    List<Secteur> findRange(int[] range);

    int count();
    
}
