/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CostingContratQte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CostingContratQteFacadeLocal {

    void create(CostingContratQte costingContratQte);

    void edit(CostingContratQte costingContratQte);

    void remove(CostingContratQte costingContratQte);

    CostingContratQte find(Object id);

    List<CostingContratQte> findAll();

    List<CostingContratQte> findRange(int[] range);

    int count();

    Long nextId();

    CostingContratQte findByIdPlafondBudget(long idMoyens, int idUniteCosting);

    List<CostingContratQte> findByIdStructureIdBudget(long idStructure, int idBudget);

    List<CostingContratQte> findByIdStructureIdBudgetIdPeriode(long idStructure, int idBudget, int idPeriode);

    void deleteByIdcontratIdMoyen(Long idContrat, long idMoyens);

    List<CostingContratQte> findByIdContrat(long idContrat);

    List<CostingContratQte> findByIdContrat(long idContrat, boolean etatTache);

    List<CostingContratQte> findByIdStructureIdBudgetIdPeriodeIdActivite(long idStructure, int idBudget, int idPeriode, long idActivite , boolean etat);

}
