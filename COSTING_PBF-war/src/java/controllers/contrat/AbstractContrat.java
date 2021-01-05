/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.contrat;

import controllers.util.SessionMBean;
import entities.Budget;
import entities.Contrat;
import entities.ContratMoyens;
import entities.ContratTache;
import entities.Frequence;
import entities.Moyens;
import entities.Periode;
import entities.Structure;
import entities.Tache;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.BudgetFacadeLocal;
import sessions.ContratFacadeLocal;
import sessions.ContratMoyensFacadeLocal;
import sessions.ContratTacheFacadeLocal;
import sessions.FrequenceFacadeLocal;
import sessions.MoyensFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TacheFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractContrat {

    @EJB
    protected ContratFacadeLocal contratFacadeLocal;
    protected Contrat contrat = new Contrat();
    protected List<Contrat> contrats = new ArrayList<>();

    @EJB
    protected FrequenceFacadeLocal frequenceFacadeLocal;
    protected List<Frequence> frequences = new ArrayList<>();

    @EJB
    protected ContratTacheFacadeLocal contratTacheFacadeLocal;
    protected List<ContratTache> contratTaches = new ArrayList<>();

    @EJB
    protected ContratMoyensFacadeLocal contratMoyensFacadeLocal;
    protected List<ContratMoyens> contratMoyens = new ArrayList<>();
    protected List<ContratMoyens> contratMoyensTrue = new ArrayList<>();
    
    

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

    @EJB
    protected TacheFacadeLocal tacheFacadeLocal;
    protected List<Tache> taches = new ArrayList<>();
    protected List<Tache> selectedTaches = new ArrayList<>();

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

    public void setListMoyens(List<Moyens> listMoyens) {
        this.listMoyens = listMoyens;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public List<Tache> getSelectedTaches() {
        return selectedTaches;
    }

    public void setSelectedTaches(List<Tache> selectedTaches) {
        this.selectedTaches = selectedTaches;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public List<Frequence> getFrequences() {
        frequences = frequenceFacadeLocal.findAll();
        return frequences;
    }

}
