/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.sous_critere;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.Souscritere;
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
public class SousCritereController extends AbstractSousCritere implements Serializable {
    
    public SousCritereController() {
        
    }
    
    public void prepareCreate() {
        critere = new Critere();
        critere.setIdcritere(-1);
        souscritere = new Souscritere();
        souscritere.setDetail("-");
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }
    
    public void prepareEdit(Souscritere sc) {
        this.souscritere = sc;
        if (souscritere.getIdcritere() == null) {
            critere = new Critere();
        } else {
            critere = sc.getIdcritere();
        }
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }
    
    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/critere/critere.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void save() {
        try {
            if (critere.getIdcritere() == -1) {
                JsfUtil.addErrorMessage("Veuillez sélectionner sélectionner le critères");
                return;
            }
            if ("Create".equals(mode)) {
                souscritere.setIdsouscritere(souscritereFacadeLocal.nextId());
                souscritere.setIdcritere(critere);
                souscritereFacadeLocal.create(souscritere);
                critere = new Critere();
                souscritere = new Souscritere();
                
                RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (souscritere != null) {
                    souscritere.setIdcritere(critereFacadeLocal.find(critere.getIdcritere()));
                    souscritereFacadeLocal.edit(souscritere);
                    souscritere = new Souscritere();
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
    
    public void delete(Souscritere sc) {
        try {
            souscritereFacadeLocal.remove(sc);
            souscritere = new Souscritere();
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
}
