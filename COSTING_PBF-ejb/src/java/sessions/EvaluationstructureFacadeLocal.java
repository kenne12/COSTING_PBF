/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationstructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationstructureFacadeLocal {

    void create(Evaluationstructure evaluationstructure);

    void edit(Evaluationstructure evaluationstructure);

    void remove(Evaluationstructure evaluationstructure);

    Evaluationstructure find(Object id);

    List<Evaluationstructure> findAll();

    List<Evaluationstructure> findRange(int[] range);

    int count();

    Long nextId();

    List<Evaluationstructure> findByIdStructure(Long idStructure, int idBudget, int idPeriode);

}
