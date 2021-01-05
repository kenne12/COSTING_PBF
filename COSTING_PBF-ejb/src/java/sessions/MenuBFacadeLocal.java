/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.MenuB;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface MenuBFacadeLocal {

    void create(MenuB menuB);

    void edit(MenuB menuB);

    void remove(MenuB menuB);

    MenuB find(Object id);

    List<MenuB> findAll();

    List<MenuB> findRange(int[] range);

    int count();
    
}
