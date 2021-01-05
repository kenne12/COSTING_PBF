/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.structure;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Pyramide;
import entities.Statutstructure;
import entities.Structure;
import entities.Typestructure;
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
public class StructureController extends AbstractStructure implements Serializable {
    
    public StructureController() {
        
    }
    
    private void initInstitution() {
        structure = new Structure();
        typestructure = new Typestructure();
        structure.setAxeintervention("-");
        structure.setArretecreation("-");
        structure.setContractant("MINISTERE DE LA SANTE");
        structure.setRepContractant("Dr MANAOUDA MALACHIE");
        structure.setTitrRepContractant("MINISTRE DE LA SANTE");
        structure.setContractantSName("MINSANTE");
        structure.setArticleContractantFullName("Le");
        structure.setArticleContracteFullName("Le");
        
        structure.setDeLaDuContracte("De la");
        structure.setDeLaDuContractant("Du");
        
        structure.setContracteSName("-");
        structure.setArticle("-");
        
        structure.setRepContracte("-");
        structure.setTitre_contracte("-");
        structure.setContracteFullName("-");
        structure.setContracteRepresente("representé");
        structure.setContractantRepresente("representé");
        statutstructure = new Statutstructure();
        institution = SessionMBean.getInstitution();
        pyramide = new Pyramide();
    }
    
    public void prepareCreate() {
        mode = "Create";
        this.initInstitution();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/structure/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareEdit(Structure s) {
        this.structure = s;
        if (structure != null) {
            institution = s.getIdinstitution();
            typestructure = s.getIdtypestructure();
            statutstructure = s.getIdstatutstructure();
            pyramide = s.getIdpyramide();
            mode = "Edit";
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/structure/Ajout.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/structure/structure.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateStructureContracte() {
        if (!structure.getNom().isEmpty()) {
            structure.setContracteFullName(structure.getNom());
        } else {
            structure.setContracteFullName("-");
        }
    }
    
    public void save() {
        try {
            if ("Create".equals(mode)) {
                
                structure.setIdstructure(structureFacadeLocal.nextId());
                structure.setIdinstitution(institution);
                structure.setIdstatutstructure(statutstructure);
                structure.setIdtypestructure(typestructure);
                structure.setIdpyramide(pyramide);
                structure.setEtat("Actif");
                structure.setActif(true);
                structure.setNomrespo("-");
                
                structure.setDateEnregistre(new Date());
                structure.setDerniereModif(new Date());
                structure.setDateouverture(new Date());
                
                structureFacadeLocal.create(structure);
                this.initInstitution();
                
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (structure != null) {
                    if (typestructure.getIdtypestructure() != null) {
                        structure.setIdtypestructure(typestructureFacadeLocal.find(typestructure.getIdtypestructure()));
                    }
                    
                    if (statutstructure.getIdstatutstructure() != null) {
                        structure.setIdstatutstructure(statutstructureFacadeLocal.find(statutstructure.getIdstatutstructure()));
                    }
                    
                    if (pyramide.getIdpyramide() != null) {
                        structure.setIdpyramide(pyramideFacadeLocal.find(pyramide.getIdpyramide()));
                    }
                    
                    if (institution.getIdinstitution() != null) {
                        structure.setIdinstitution(institutionFacadeLocal.find(institution.getIdinstitution()));
                    }
                    
                    structureFacadeLocal.edit(structure);
                    
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
    
    public void delete(Structure s) {
        try {
            structureFacadeLocal.remove(s);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
}
