/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.element_cout;

import controllers.util.JsfUtil;
import entities.UniteCosting;
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
public class ElementCoutController extends AbstractElementCout implements Serializable {

    public ElementCoutController() {

    }

    public void prepareCreate() {
        uniteCosting = new UniteCosting();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('ElementCoutCreateDialog').show()");
    }

    public void prepareEdit(UniteCosting c) {
        this.uniteCosting = c;
        if (uniteCosting != null) {
            mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('ElementCoutCreateDialog').show()");
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/uniteCosting/uniteCosting.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                uniteCosting.setIduniteCosting(uniteCostingFacadeLocal.nextId());
                uniteCostingFacadeLocal.create(uniteCosting);
                uniteCosting = new UniteCosting();
                detail = true;
                RequestContext.getCurrentInstance().execute("PF('ElementCoutCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (uniteCosting != null) {
                    uniteCostingFacadeLocal.edit(uniteCosting);
                    uniteCosting = new UniteCosting();
                    detail = true;
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    RequestContext.getCurrentInstance().execute("PF('ElementCoutCreateDialog').hide()");
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
            if (uniteCosting != null) {
                uniteCostingFacadeLocal.remove(uniteCosting);
                uniteCosting = new UniteCosting();
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

    public void delete(UniteCosting c) {
        try {
            if (c != null) {
                uniteCostingFacadeLocal.remove(c);
                uniteCosting = new UniteCosting();
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
