/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CostingPbQte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CostingPbQteFacadeLocal {

    void create(CostingPbQte costingPbQte);

    void edit(CostingPbQte costingPbQte);

    void remove(CostingPbQte costingPbQte);

    CostingPbQte find(Object id);

    List<CostingPbQte> findAll();

    List<CostingPbQte> findRange(int[] range);

    int count();

    CostingPbQte findByIdPlafondBudget(long idPlafondBudget, int idUniteCosting);

    List<CostingPbQte> findByIdStructureIdBudget(long idStructure, int idBudget);

}
