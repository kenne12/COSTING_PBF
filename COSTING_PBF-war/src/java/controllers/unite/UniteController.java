/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.unite;

import controllers.util.JsfUtil;
import entities.Unite;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class UniteController extends AbstractUnite implements Serializable {

    public UniteController() {

    }

    public void prepareCreate() {
        unite = new Unite();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('UniteCreateDialog').show()");
    }

    public void prepareEdit(Unite c) {
        this.unite = c;
        if (unite != null) {
            mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('UniteCreateDialog').show()");
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/unite/unite.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                unite.setIdunite(uniteFacadeLocal.nextId());
                uniteFacadeLocal.create(unite);
                unite = new Unite();
                detail = true;
                RequestContext.getCurrentInstance().execute("PF('UniteCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (unite != null) {
                    uniteFacadeLocal.edit(unite);
                    unite = new Unite();
                    detail = true;
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    RequestContext.getCurrentInstance().execute("PF('UniteCreateDialog').hide()");
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
            if (unite != null) {
                uniteFacadeLocal.remove(unite);
                unite = new Unite();
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

    public void delete(Unite c) {
        try {
            if (c != null) {
                uniteFacadeLocal.remove(c);
                unite = new Unite();
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
