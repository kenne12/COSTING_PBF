/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.activite;

import controllers.util.SessionMBean;
import entities.Action;
import entities.Activite;
import entities.Programme;
import entities.Rang;
import entities.Structure;
import entities.Typeactivite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.ActionFacadeLocal;
import sessions.ActiviteFacadeLocal;
import sessions.ProgrammeFacadeLocal;
import sessions.RangFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypeactiviteFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractActivite {

    @EJB
    protected ActiviteFacadeLocal activiteFacadeLocal;
    protected Activite activite = new Activite();
    protected List<Activite> activites = new ArrayList<>();

    @EJB
    protected TypeactiviteFacadeLocal typeactiviteFacadeLocal;
    protected Typeactivite typeactivite = new Typeactivite();
    protected List<Typeactivite> typeactivites = new ArrayList<>();

    @EJB
    protected RangFacadeLocal rangFacadeLocal;
    protected Rang rang = new Rang();
    protected List<Rang> rangs = new ArrayList<>();

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

    protected Routine routine = new Routine();

    public AbstractActivite() {
    }

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";
    protected boolean detail = true;

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public List<Programme> getProgrammes() {
        programmes = programmeFacadeLocal.findAll();
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
        activites = activiteFacadeLocal.findByIdInstitution(SessionMBean.getInstitution().getIdinstitution());
        return activites;
    }

    public Typeactivite getTypeactivite() {
        return typeactivite;
    }

    public void setTypeactivite(Typeactivite typeactivite) {
        this.typeactivite = typeactivite;
    }

    public List<Typeactivite> getTypeactivites() {
        typeactivites = typeactiviteFacadeLocal.findAll();
        return typeactivites;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        structures = structureFacadeLocal.findAllRange();
        return structures;
    }

    public Rang getRang() {
        return rang;
    }

    public void setRang(Rang rang) {
        this.rang = rang;
    }

    public List<Rang> getRangs() {
        rangs = rangFacadeLocal.findAll();
        return rangs;
    }

}
