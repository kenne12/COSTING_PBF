/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.activite;

import controllers.util.JsfUtil;
import entities.Action;
import entities.Activite;
import entities.Programme;
import entities.Typeactivite;
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
public class ActiviteController extends AbstractActivite implements Serializable {

    public ActiviteController() {

    }

    private void initActivite() {
        programme = new Programme();
        action = new Action();
        typeactivite = new Typeactivite();
        activite = new Activite();

        activite.setPrio(true);
        activite.setObjectif("-");
        activite.setResponsables("-");
        activite.setJustification("-");
        activite.setEtat("Actif");
        activite.setPartisprenantes("-");
        activite.setAutreconcerne("-");

        activite.setCumulextrants("-");
        activite.setCumulindicateurs("-");
    }

    public void prepareCreate() {
        mode = "Create";
        this.initActivite();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/activite/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        if (activite != null) {
            action = activite.getIdaction();
            typeactivite = activite.getIdtypeactivite();
            structure = activite.getIdstructure();
            rang = activite.getIdrang();
            mode = "Edit";
        }
    }

    public void prepareEdit(Activite a) {
        this.activite = a;
        if (activite != null) {
            action = a.getIdaction();
            typeactivite = a.getIdtypeactivite();
            structure = a.getIdstructure();
            rang = a.getIdrang();
            programme = a.getIdaction().getIdprogramme();
            this.filtreActionByProgram();
            mode = "Edit";
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/activite/Ajout.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/activite/activite.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filtreActionByProgram() {
        try {
            this.actions.clear();
            if (programme.getIdprogramme() != null) {
                actions = actionFacadeLocal.findByProgramme(programme.getIdprogramme());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {

                activite.setIdactivite(activiteFacadeLocal.nextId());
                activite.setIdaction(action);
                activite.setIdstructure(structure);
                activite.setIdtypeactivite(typeactivite);
                activite.setIdrang(rang);

                activite.setDateEnregistre(new Date());
                activite.setDerniereModif(new Date());
                activite.setEtat("Actif");
                activiteFacadeLocal.create(activite);
                this.initActivite();
                detail = true;
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (activite != null) {
                    if (action.getIdaction() != null) {
                        activite.setIdaction(actionFacadeLocal.find(action.getIdaction()));
                    }

                    if (structure.getIdstructure() != null) {
                        activite.setIdstructure(structureFacadeLocal.find(structure.getIdstructure()));
                    }

                    if (typeactivite.getIdtypeactivite() != null) {
                        activite.setIdtypeactivite(typeactiviteFacadeLocal.find(typeactivite.getIdtypeactivite()));
                    }

                    if (rang.getIdrang() != null) {
                        activite.setIdrang(rangFacadeLocal.find(rang.getIdrang()));
                    }

                    activiteFacadeLocal.edit(activite);
                    detail = true;
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

    public void delete() {
        try {
            if (activite != null) {
                activiteFacadeLocal.remove(activite);
                activite = new Activite();
                detail = true;
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Activite a) {
        try {
            if (a != null) {
                activiteFacadeLocal.remove(a);
                activite = new Activite();
                detail = true;
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

}
