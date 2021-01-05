/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.action;

import controllers.util.SessionMBean;
import entities.Action;
import entities.Programme;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.ActionFacadeLocal;
import sessions.ProgrammeFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractAction {

    @EJB
    protected ActionFacadeLocal actionFacadeLocal;
    protected Action action = new Action();
    protected List<Action> actions = new ArrayList<>();

    @EJB
    protected ProgrammeFacadeLocal programmeFacadeLocal;
    protected Programme programme = new Programme();
    protected List<Programme> programmes = new ArrayList<>();

    protected Routine routine = new Routine();

    public AbstractAction() {
    }

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

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
        actions = actionFacadeLocal.findByInstitution(SessionMBean.getInstitution().getIdinstitution());
        return actions;
    }

}
