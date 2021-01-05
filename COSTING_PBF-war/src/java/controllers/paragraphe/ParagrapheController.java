/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.paragraphe;

import controllers.util.JsfUtil;
import entities.Imputation;
import entities.Origine;
import entities.Sousrubrique;
import entities.Unite;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
//import org.primefaces.context.RequestContext;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ParagrapheController extends AbstractParagraphe implements Serializable {
    
    public ParagrapheController() {
        
    }
    
    private void initImputation() {
        imputation = new Imputation();
        imputation.setIdunite(new Unite());
        imputation.setEtat("Actif");
        imputation.setDescription("-");
        imputation.setCriterevalidation("-");
        imputation.setBonus(true);
        origine = new Origine();
        sousrubrique = new Sousrubrique();
    }
    
    public void prepareCreate() {
        mode = "Create";
        this.initImputation();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/paragraphe/Ajout.html");
        } catch (Exception e) {
        }
    }
    
    public void prepareEdit(Imputation u) {
        this.imputation = u;        
        origine = imputation.getIdorigine();
        sousrubrique = imputation.getIdsousrubrique();
        mode = "Edit";
        if (imputation.getIdunite() == null) {
            imputation.setIdunite(new Unite(-1));
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/paragraphe/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/paragraphe/imputation.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void save() {
        try {
            if (imputation.getIdunite().getIdunite().equals(-1)) {
                JsfUtil.addWarningMessage("Veuillez renseigner l'unité");
                return;
            }
            if ("Create".equals(mode)) {
                imputation.setIdimputation(imputationFacadeLocal.nextId());
                imputation.setDateEnregistre(new Date());
                imputation.setDerniereModif(new Date());
                imputation.setIdorigine(origine);
                imputation.setEtat("Actif");
                imputation.setIdsousrubrique(sousrubrique);
                imputationFacadeLocal.create(imputation);
                this.initImputation();
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (imputation != null) {
                    if (origine.getIdorigine() != null) {
                        imputation.setIdorigine(origineFacadeLocal.find(origine.getIdorigine()));
                    }
                    
                    if (sousrubrique.getIdsousrubrique() != null) {
                        imputation.setIdsousrubrique(sousrubriqueFacadeLocal.find(sousrubrique.getIdsousrubrique()));
                    }
                    
                    imputation.setIdunite(uniteFacadeLocal.find(imputation.getIdunite().getIdunite()));
                    
                    imputationFacadeLocal.edit(imputation);
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
    
    public void delete(Imputation u) {
        try {
            if (u != null) {
                imputationFacadeLocal.remove(u);
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
}
