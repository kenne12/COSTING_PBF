/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ContratMoyens;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ContratMoyensFacadeLocal {

    void create(ContratMoyens contratMoyens);

    void edit(ContratMoyens contratMoyens);

    void remove(ContratMoyens contratMoyens);

    ContratMoyens find(Object id);

    List<ContratMoyens> findAll();

    List<ContratMoyens> findRange(int[] range);

    int count();

    List<ContratMoyens> findByIdContrat(Long idContrat);

    List<ContratMoyens> findByIdContrat(Long idContrat, boolean etatTache);

    List<ContratMoyens> findByIdPeriodeFb(int idPeriode, long idInstitution, int idBudget);

    List<ContratMoyens> findByIdPeriodeFbEtatTache(int idPeriode, long idInstitution, int idBudget, boolean etatTache);

    List<ContratMoyens> findByIdPeriodeFa(int idPeriode, long idInstitution, int idBudget);

    List<ContratMoyens> findByIdPeriodeFaEtatTache(int idPeriode, long idInstitution, int idBudget, boolean etatTache);

    void deleteByIdContrat(Long idContrat);

    List<ContratMoyens> findByIdTache(Long idTache);

    List<ContratMoyens> findByIdPeriodeIdActivite(int idPeriode, long idInstitution, int idBudget, long idActivite, boolean etatTache);

}
