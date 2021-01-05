/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation;

import controllers.util.SessionMBean;
import entities.Action;
import entities.Activite;
import entities.Bailleurfond;
import entities.Budget;
import entities.Detailsc;
import entities.Evaluationstructure;
import entities.Periode;
import entities.Programme;
import entities.Risque;
import entities.Structure;
import entities.Tache;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.ActionFacadeLocal;
import sessions.ActiviteFacadeLocal;
import sessions.BailleurfondFacadeLocal;
import sessions.ContratFacadeLocal;
import sessions.DetailscFacadeLocal;
import sessions.EvaluationstructureFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.ProgrammeFacadeLocal;
import sessions.RisqueFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TacheFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractEvaluation {

    @EJB
    protected EvaluationstructureFacadeLocal evaluationstructureFacadeLocal;
    protected Evaluationstructure evaluationstructure = new Evaluationstructure();
    protected List<Evaluationstructure> evaluationstructures = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected Periode periode = new Periode();
    protected List<Periode> periodes = new ArrayList<>();

    @EJB
    protected DetailscFacadeLocal detailscFacadeLocal;
    protected List<Detailsc> listDetailsc = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected ActionFacadeLocal actionFacadeLocal;
    protected Action action = new Action();
    protected List<Action> actions = new ArrayList<>();

    @EJB
    protected ActiviteFacadeLocal activiteFacadeLocal;
    protected Activite activite = new Activite();
    protected List<Activite> activites = new ArrayList<>();

    @EJB
    protected ProgrammeFacadeLocal programmeFacadeLocal;
    protected Programme programme = new Programme();
    protected List<Programme> programmes = new ArrayList<>();

    @EJB
    protected TacheFacadeLocal tacheFacadeLocal;
    protected Tache tache = new Tache();
    protected List<Tache> taches = new ArrayList<>();

    @EJB
    protected BailleurfondFacadeLocal bailleurfondFacadeLocal;
    protected Bailleurfond bailleurfond = new Bailleurfond();
    protected List<Bailleurfond> bailleurfonds = new ArrayList<>();

    @EJB
    protected RisqueFacadeLocal risqueFacadeLocal;
    protected Risque risque = new Risque();
    protected List<Risque> risques = new ArrayList<>();
    
    @EJB
    protected ContratFacadeLocal contratFacadeLocal;

    protected Routine routine = new Routine();

    protected double scorePrec;
    protected double scorePrev;
    protected double scoreReal;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    protected String message = "";

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

    public double getScorePrec() {
        return scorePrec;
    }

    public double getScorePrev() {
        return scorePrev;
    }

    public double getScoreReal() {
        return scoreReal;
    }

    public List<Detailsc> getListDetailsc() {
        return listDetailsc;
    }

    public String getMessage() {
        return message;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public List<Periode> getPeriodes() {
        periodes = periodeFacadeLocal.findAllRange();
        return periodes;
    }

    public List<Evaluationstructure> getEvaluationstructures() {
        return evaluationstructures;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Action> getActions() {
        return actions;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public List<Programme> getProgrammes() {
        programmes = programmeFacadeLocal.findByIdinstitution(SessionMBean.getInstitution().getIdinstitution());
        return programmes;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public Bailleurfond getBailleurfond() {
        return bailleurfond;
    }

    public void setBailleurfond(Bailleurfond bailleurfond) {
        this.bailleurfond = bailleurfond;
    }

    public List<Bailleurfond> getBailleurfonds() {
        bailleurfonds = bailleurfondFacadeLocal.findAll();
        return bailleurfonds;
    }

    public Risque getRisque() {
        return risque;
    }

    public void setRisque(Risque risque) {
        this.risque = risque;
    }

    public List<Risque> getRisques() {
        risques = risqueFacadeLocal.findAll();
        return risques;
    }

    public Evaluationstructure getEvaluationstructure() {
        return evaluationstructure;
    }

    public void setEvaluationstructure(Evaluationstructure evaluationstructure) {
        this.evaluationstructure = evaluationstructure;
    }

}
