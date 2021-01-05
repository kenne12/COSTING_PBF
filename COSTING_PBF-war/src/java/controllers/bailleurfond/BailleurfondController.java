/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.bailleurfond;

import controllers.util.JsfUtil;
import entities.Bailleurfond;
import entities.Critere;
import entities.Sourcefinancement;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class BailleurfondController extends AbstractBailleurfond implements Serializable {
    
    public BailleurfondController() {
        
    }
    
    public void prepareCreate() {
        bailleurfond = new Bailleurfond();
        sourcefinancement = new Sourcefinancement(1l);
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }
    
    public void prepareEdit(Bailleurfond b) {
        this.bailleurfond = b;
        sourcefinancement = b.getIdsourcefinancement();
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }
    
    public void save() {
        try {
            
            if (sourcefinancement.getIdsourcefinancement() == -1) {
                JsfUtil.addErrorMessage("Veuillez sélectionner sélectionner la source de financement");
                return;
            }
            
            if ("Create".equals(mode)) {
                bailleurfond.setIdbailleurfond(bailleurfondFacadeLocal.nextId());
                bailleurfond.setIdsourcefinancement(sourcefinancement);
                bailleurfondFacadeLocal.create(bailleurfond);
                bailleurfond = new Bailleurfond();
                detail = true;
                RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (bailleurfond != null) {
                    bailleurfond.setIdsourcefinancement(sourcefinancementFacadeLocal.find(sourcefinancement.getIdsourcefinancement()));
                    bailleurfondFacadeLocal.edit(bailleurfond);
                    
                    bailleurfond = new Bailleurfond();
                    detail = true;
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').hide()");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne seletionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
    public void delete(Critere c) {
        try {
            if (c != null) {
                bailleurfondFacadeLocal.remove(bailleurfond);
                bailleurfond = new Bailleurfond();
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
