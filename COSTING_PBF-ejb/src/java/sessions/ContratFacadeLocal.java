/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Contrat;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ContratFacadeLocal {

    void create(Contrat contrat);

    void edit(Contrat contrat);

    void remove(Contrat contrat);

    Contrat find(Object id);

    List<Contrat> findAll();

    List<Contrat> findRange(int[] range);

    int count();

    Long nextId();

    Contrat findByIdtructureIdperiode(Long idStructure, int idBudget, int idPeriode);

    List<Contrat> findByIdtructureIdBudget(Long idStructure, int idBudget);

    List<Contrat> findByIdInstitutionIdBudget(Long idInstitution, int idBudget);

    List<Contrat> findByIdInstitutionIdBudgetIdPeriode(Long idInstitution, int idBudget, int idPeriode);

}
