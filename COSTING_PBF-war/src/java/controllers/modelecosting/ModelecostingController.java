/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.modelecosting;

import controllers.util.JsfUtil;
import entities.Imputation;
import entities.Modelecosting;
import entities.Unite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ModelecostingController extends AbstractModelecosting implements Serializable {
    
    public ModelecostingController() {
        
    }
    
    private void initModele() {
        imputation = new Imputation();
        unite = new Unite();
        modelecosting = new Modelecosting();
    }
    
    public void prepareCreate() {
        mode = "Create";
        this.initModele();
        this.initImputation();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/costing/Ajout.html");
        } catch (Exception e) {
        }
    }
    
    public void updateCu() {
        modelecosting.setCoutunitaire(0d);
        if (imputation.getIdimputation() != null) {
            imputation = imputationFacadeLocal.find(imputation.getIdimputation());
            modelecosting.setCoutunitaire(imputation.getCoutUnitaire());
        }
    }
    
    public void prepareEdit() {
        if (modelecosting != null) {
            unite = modelecosting.getIdunite();
            imputation = modelecosting.getIdimputation();
            mode = "Edit";
        }
    }
    
    private void initImputation() {
        imputations.clear();
        List<Modelecosting> list = modelecostingFacadeLocal.findAll();
        List<Imputation> list_imp_all = imputationFacadeLocal.findAll();
        List<Imputation> list_param_used = new ArrayList<>();
        if (!list.isEmpty()) {
            for (Modelecosting mc : list) {
                list_param_used.add(mc.getIdimputation());
            }
            list_imp_all.removeAll(list_param_used);
        }
        imputations.addAll(list_imp_all);
    }
    
    public void prepareEdit(Modelecosting mc) {
        this.modelecosting = mc;
        if (mc != null) {
            unite = mc.getIdunite();
            imputation = mc.getIdimputation();
            imputations.clear();
            imputations.add(mc.getIdimputation());
            mode = "Edit";
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/costing/Ajout.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/costing/costing.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void save() {
        try {
            if ("Create".equals(mode)) {
                modelecosting.setIdmodelecosting(modelecostingFacadeLocal.nextId());
                modelecosting.setIdunite(unite);
                modelecosting.setIdimputation(imputation);
                modelecosting.setMontantannuel(modelecosting.getQuantite() * modelecosting.getCoutunitaire());
                modelecosting.setMontanttimestre(modelecosting.getMontantannuel() / 4);
                modelecostingFacadeLocal.create(modelecosting);
                
                this.initImputation();
                detail = true;
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (modelecosting != null) {
                    if (unite.getIdunite() != null) {
                        modelecosting.setIdunite(uniteFacadeLocal.find(unite.getIdunite()));
                    }
                    modelecosting.setMontantannuel(modelecosting.getQuantite() * modelecosting.getCoutunitaire());
                    modelecosting.setMontanttimestre(modelecosting.getMontantannuel() / 4);
                    modelecostingFacadeLocal.edit(modelecosting);
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
            if (modelecosting != null) {
                modelecostingFacadeLocal.remove(modelecosting);
                imputation = new Imputation();
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
    
    public void delete(Modelecosting mc) {
        try {
            if (mc != null) {
                modelecostingFacadeLocal.remove(mc);
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
