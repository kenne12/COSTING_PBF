/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Moyens;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface MoyensFacadeLocal {

    void create(Moyens moyens);

    void edit(Moyens moyens);

    void remove(Moyens moyens);

    Moyens find(Object id);

    List<Moyens> findAll();

    List<Moyens> findRange(int[] range);

    int count();

    Long nextId();

    List<Moyens> findByStructureAnnee(Long idstructure, Long idannee);

    List<Moyens> findByTache(Long idtache);

    List<Moyens> findByStructureAnneeIdimputation(Long idstructure, Long idannee, Long idimputation);

    void deleteByIdTache(Long idtache);

}
