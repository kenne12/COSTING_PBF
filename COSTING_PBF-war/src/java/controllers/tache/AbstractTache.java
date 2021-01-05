/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.tache;

import controllers.util.SessionMBean;
import entities.Action;
import entities.Activite;
import entities.Bailleurfond;
import entities.Evaluationstructure;
import entities.Imputation;
import entities.Moyens;
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
import sessions.EvaluationstructureFacadeLocal;
import sessions.ImputationFacadeLocal;
import sessions.MoyensFacadeLocal;
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
public class AbstractTache {

    @EJB
    protected TacheFacadeLocal tacheFacadeLocal;
    protected Tache tache = new Tache();
    protected List<Tache> taches = new ArrayList<>();

    @EJB
    protected EvaluationstructureFacadeLocal evaluationstructureFacadeLocal;
    protected Evaluationstructure evaluationstructure = new Evaluationstructure();
    protected List<Evaluationstructure> evaluationstructures = new ArrayList<>();

    @EJB
    protected MoyensFacadeLocal moyensFacadeLocal;
    protected Moyens moyens = new Moyens();
    protected List<Moyens> listMoyens = new ArrayList<>();

    @EJB
    protected ImputationFacadeLocal imputationFacadeLocal;
    protected Imputation imputation = new Imputation();
    protected List<Imputation> imputations = new ArrayList<>();

    @EJB
    protected ActiviteFacadeLocal activiteFacadeLocal;
    protected Activite activite = new Activite();
    protected List<Activite> activites = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected ActionFacadeLocal actionFacadeLocal;
    protected Action action = new Action();
    protected List<Action> actions = new ArrayList<>();

    @EJB
    protected ProgrammeFacadeLocal programmeFacadeLocal;
    protected Programme programme = new Programme();
    protected List<Programme> programmes = new ArrayList<>();

    @EJB
    protected BailleurfondFacadeLocal bailleurfondFacadeLocal;
    protected Bailleurfond bailleurfond = new Bailleurfond();
    protected List<Bailleurfond> bailleurfonds = new ArrayList<>();

    @EJB
    protected RisqueFacadeLocal risqueFacadeLocal;
    protected Risque risque = new Risque();
    protected List<Risque> risques = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected Periode periode = new Periode();
    protected List<Periode> periodes = new ArrayList<>();

    protected double total;

    protected Routine routine = new Routine();

    public AbstractTache() {
    }

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";
    protected String mode_paragraphe = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Action> getActions() {
        return actions;
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

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public List<Moyens> getListMoyens() {
        return listMoyens;
    }

    public void setListMoyens(List<Moyens> listMoyens) {
        this.listMoyens = listMoyens;
    }

    public List<Imputation> getImputations() {
        imputations = imputationFacadeLocal.findAllOrder();
        return imputations;
    }

    public void setImputations(List<Imputation> imputations) {
        this.imputations = imputations;
    }

    public Bailleurfond getBailleurfond() {
        return bailleurfond;
    }

    public void setBailleurfond(Bailleurfond bailleurfond) {
        this.bailleurfond = bailleurfond;
    }

    public List<Bailleurfond> getBailleurfonds() {
        bailleurfonds = bailleurfondFacadeLocal.findAllRange();
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

    public Moyens getMoyens() {
        return moyens;
    }

    public void setMoyens(Moyens moyens) {
        this.moyens = moyens;
    }

    public Imputation getImputation() {
        return imputation;
    }

    public void setImputation(Imputation imputation) {
        this.imputation = imputation;
    }

    public List<Evaluationstructure> getEvaluationstructures() {
        return evaluationstructures;
    }

    public Evaluationstructure getEvaluationstructure() {
        return evaluationstructure;
    }

    public void setEvaluationstructure(Evaluationstructure evaluationstructure) {
        this.evaluationstructure = evaluationstructure;
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

}
