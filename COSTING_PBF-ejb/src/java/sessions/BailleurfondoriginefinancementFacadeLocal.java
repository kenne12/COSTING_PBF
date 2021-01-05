/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Bailleurfondoriginefinancement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface BailleurfondoriginefinancementFacadeLocal {

    void create(Bailleurfondoriginefinancement bailleurfondoriginefinancement);

    void edit(Bailleurfondoriginefinancement bailleurfondoriginefinancement);

    void remove(Bailleurfondoriginefinancement bailleurfondoriginefinancement);

    Bailleurfondoriginefinancement find(Object id);

    List<Bailleurfondoriginefinancement> findAll();

    List<Bailleurfondoriginefinancement> findRange(int[] range);

    int count();

    List<Bailleurfondoriginefinancement> findByIdBailleurfond(long idBailleurfond);

}
