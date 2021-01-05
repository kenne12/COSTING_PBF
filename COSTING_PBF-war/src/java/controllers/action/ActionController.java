/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.action;

import controllers.util.JsfUtil;
import entities.Action;
import entities.Programme;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ActionController extends AbstractAction implements Serializable {

    public ActionController() {

    }

    private void initAction() {
        programme = new Programme();
        action = new Action();
        action.setBaseline(0D);
        action.setObjectifs("-");
        action.setRespomeo("-");
        action.setEffet("-");
        action.setIndicateur("-");
        action.setCible(0D);
        action.setBaseline(0d);
        action.setEtat("Actif");
    }

    public void prepareCreate() {
        mode = "Create";
        this.initAction();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/action/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit(Action a) {
        this.action = a;
        programme = a.getIdprogramme();
        mode = "Edit";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/action/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/action/action.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                action.setIdaction(actionFacadeLocal.nextId());
                action.setIdprogramme(programme);
                action.setDateEnregistre(new Date());
                action.setDerniereModif(new Date());
                action.setEtat("Actif");
                actionFacadeLocal.create(action);
                this.initAction();
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (action != null) {
                    if (programme.getIdprogramme() != null) {
                        action.setIdprogramme(programmeFacadeLocal.find(programme.getIdprogramme()));
                    }

                    actionFacadeLocal.edit(action);

                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne seletionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Action a) {
        try {
            actionFacadeLocal.remove(a);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
