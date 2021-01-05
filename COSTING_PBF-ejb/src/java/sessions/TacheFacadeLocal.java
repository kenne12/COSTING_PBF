/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Tache;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TacheFacadeLocal {

    void create(Tache tache);

    void edit(Tache tache);

    void remove(Tache tache);

    Tache find(Object id);

    List<Tache> findAll();

    List<Tache> findRange(int[] range);

    int count();

    Long nextId();

    List<Tache> findByStructureAnne(Long idstructure, Long idannee);

    List<Tache> findByIdStructureIdAnneIdPeriode(Long idstructure, Long idannee, int idPeriode);

    List<Tache> findByIdInstitution(Long idInstitution, Long idannee);

}
