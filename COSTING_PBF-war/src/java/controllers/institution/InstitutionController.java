/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.institution;

import controllers.util.JsfUtil;
import entities.Soussecteur;
import entities.Institution;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class InstitutionController extends AbstractInstitution implements Serializable {

    public InstitutionController() {

    }

    private void initInstitution() {
        institution = new Institution();
        soussecteur = new Soussecteur();
        institution.setSigle("-");
        institution.setCode("-");
        institution.setChapitre("-");
        institution.setChoixstrategique("-");
        institution.setEtat("Actif");
    }

    public void prepareCreate() {
        mode = "Create";
        this.initInstitution();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/institution/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit(Institution u) {
        this.institution = u;
        soussecteur = u.getIdsoussecteur();
        mode = "Edit";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/institution/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/institution/institution.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                institution.setIdinstitution(institutionFacadeLocal.nextId());
                institution.setDateEnregistre(new Date());
                institution.setDerniereModif(new Date());
                institution.setIdsoussecteur(soussecteur);
                institutionFacadeLocal.create(institution);
                this.initInstitution();

                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (institution != null) {
                    if (soussecteur.getIdsoussecteur() != null) {
                        institution.setIdsoussecteur(soussecteurFacadeLocal.find(soussecteur.getIdsoussecteur()));
                    }
                    institutionFacadeLocal.edit(institution);
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

    @Transactional
    public void delete(Institution u) {
        try {
            institutionFacadeLocal.remove(u);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
}
