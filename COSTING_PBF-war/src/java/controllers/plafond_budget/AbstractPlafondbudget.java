/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.plafond_budget;

import controllers.util.SessionMBean;
import entities.Budget;
import entities.CostingPbQte;
import entities.Plafondbudget;
import entities.Structure;
import entities.UniteCosting;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.BudgetFacadeLocal;
import sessions.CostingPbQteFacadeLocal;
import sessions.ModelecostingFacadeLocal;
import sessions.PlafondbudgetFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.UniteCostingFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractPlafondbudget {

    @EJB
    protected PlafondbudgetFacadeLocal plafondbudgetFacadeLocal;
    protected Plafondbudget plafondbudget = new Plafondbudget();
    protected List<Plafondbudget> plafondbudgets = new ArrayList<>();

    @EJB
    protected CostingPbQteFacadeLocal costingPbQteFacadeLocal;
    protected List<CostingPbQte> costingPbQtes = new ArrayList<>();
    protected List<CostingPbQte> costingPbQtesAll = new ArrayList<>();

    @EJB
    protected UniteCostingFacadeLocal uniteCostingFacadeLocal;
    protected List<UniteCosting> uniteCostings = new ArrayList<>();
    protected List<UniteCosting> selectedUniteCostings = new ArrayList<>();

    @EJB
    protected ModelecostingFacadeLocal modelecostingFacadeLocal;

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected BudgetFacadeLocal budgetFacadeLocal;
    protected Budget budget = SessionMBean.getBudget();
    protected List<Budget> budgets = new ArrayList<>();

    protected Routine routine = new Routine();

    protected Integer coefficient = 0;
    protected Double montantAnnuel = 0d;
    protected Double montantTrim = 0d;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";
    protected String message = "";
    protected boolean detail = true;

    public boolean isDetail() {
        return detail;
    }

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Plafondbudget getPlafondbudget() {
        return plafondbudget;
    }

    public void setPlafondbudget(Plafondbudget plafondbudget) {
        this.plafondbudget = plafondbudget;
    }

    public List<Plafondbudget> getPlafondbudgets() {
        return plafondbudgets;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        structures = structureFacadeLocal.findByIdinstitution(SessionMBean.getInstitution().getIdinstitution());
        return structures;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Budget> getBudgets() {
        budgets = budgetFacadeLocal.findAll();
        return budgets;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public Double getMontantAnnuel() {
        return montantAnnuel;
    }

    public Double getMontantTrim() {
        return montantTrim;
    }

    public List<UniteCosting> getUniteCostings() {
        return uniteCostings;
    }

    public List<UniteCosting> getSelectedUniteCostings() {
        return selectedUniteCostings;
    }

    public void setSelectedUniteCostings(List<UniteCosting> selectedUniteCostings) {
        this.selectedUniteCostings = selectedUniteCostings;
    }

    public String getMessage() {
        return message;
    }

    public List<CostingPbQte> getCostingPbQtes() {
        return costingPbQtes;
    }

    public List<CostingPbQte> getCostingPbQtesAll() {
        return costingPbQtesAll;
    }

}
