/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ContratTache;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ContratTacheFacadeLocal {

    void create(ContratTache contratTache);

    void edit(ContratTache contratTache);

    void remove(ContratTache contratTache);

    ContratTache find(Object id);

    List<ContratTache> findAll();

    List<ContratTache> findRange(int[] range);

    int count();

    List<ContratTache> findByIdContrat(Long idContrat);

    ContratTache findByIdContratIdtache(Long idContrat, long idTache);

    List<ContratTache> findByIdContrat(Long idContrat, boolean etatTache);

    void deleteByIdContrat(Long idContrat);

}
