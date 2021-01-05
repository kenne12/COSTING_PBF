/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.costing_contrat;

import controllers.util.SessionMBean;
import entities.Budget;
import entities.Contrat;
import entities.ContratMoyens;
import entities.ContratTache;
import entities.CostingContratQte;
import entities.Frequence;
import entities.Moyens;
import entities.Periode;
import entities.Structure;
import entities.UniteCosting;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.BudgetFacadeLocal;
import sessions.ContratFacadeLocal;
import sessions.ContratMoyensFacadeLocal;
import sessions.ContratTacheFacadeLocal;
import sessions.CostingContratQteFacadeLocal;
import sessions.FrequenceFacadeLocal;
import sessions.MoyensFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.UniteCostingFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractCostingContrat {

    @EJB
    protected ContratFacadeLocal contratFacadeLocal;
    protected Contrat contrat = new Contrat();
    protected List<Contrat> contrats = new ArrayList<>();

    @EJB
    protected FrequenceFacadeLocal frequenceFacadeLocal;
    protected List<Frequence> frequences = new ArrayList<>();

    @EJB
    protected ContratTacheFacadeLocal contratTacheFacadeLocal;
    protected ContratTache contratTache = new ContratTache();
    protected List<ContratTache> contratTaches = new ArrayList<>();

    @EJB
    protected ContratMoyensFacadeLocal contratMoyensFacadeLocal;
    protected ContratMoyens contratMoyen = new ContratMoyens();
    protected List<ContratMoyens> contratMoyens = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected BudgetFacadeLocal budgetFacadeLocal;
    protected Budget budget = SessionMBean.getBudget();
    protected List<Budget> budgets = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected List<Periode> periodes = new ArrayList<>();

    @EJB
    protected MoyensFacadeLocal moyensFacadeLocal;
    protected List<Moyens> listMoyens = new ArrayList<>();
    protected List<Moyens> selectedMoyens = new ArrayList<>();

    @EJB
    protected CostingContratQteFacadeLocal costingContratQteFacadeLocal;
    protected List<CostingContratQte> costingContratQtes = new ArrayList<>();
    protected List<CostingContratQte> costingContratQtesAll = new ArrayList<>();

    @EJB
    protected UniteCostingFacadeLocal uniteCostingFacadeLocal;
    protected List<UniteCosting> uniteCostings = new ArrayList<>();
    protected List<UniteCosting> selectedUniteCostings = new ArrayList<>();

    protected double montantTotal;

    protected Routine routine = new Routine();
    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public List<ContratTache> getContratTaches() {
        return contratTaches;
    }

    public List<ContratMoyens> getContratMoyens() {
        return contratMoyens;
    }

    public List<Periode> getPeriodes() {
        return periodes;
    }

    public List<Moyens> getListMoyens() {
        return listMoyens;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public List<Frequence> getFrequences() {
        return frequences;
    }

    public List<Moyens> getSelectedMoyens() {
        return selectedMoyens;
    }

    public void setSelectedMoyens(List<Moyens> selectedMoyens) {
        this.selectedMoyens = selectedMoyens;
    }

    public ContratTache getContratTache() {
        return contratTache;
    }

    public void setContratTache(ContratTache contratTache) {
        this.contratTache = contratTache;
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

    public List<CostingContratQte> getCostingContratQtes() {
        return costingContratQtes;
    }

    public List<CostingContratQte> getCostingContratQtesAll() {
        return costingContratQtesAll;
    }

}
