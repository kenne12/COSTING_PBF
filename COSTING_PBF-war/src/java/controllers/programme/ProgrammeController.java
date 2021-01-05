/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.programme;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Axestrategique;
import entities.Institution;
import entities.Programme;
import entities.Soussecteur;
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
public class ProgrammeController extends AbstractProgramme implements Serializable {

    public ProgrammeController() {

    }

    private void initProgramme() {

        programme = new Programme();
        programme.setBaseline(0d);
        programme.setObjectifs("-");
        programme.setObjectifstrategique("-");
        programme.setImpact("-");
        programme.setIndicateur("-");
        programme.setCible(0d);
        programme.setEtat("Actif");

        institution = SessionMBean.getInstitution();
        axestrategique = new Axestrategique();
        soussecteur = new Soussecteur();
    }

    public void prepareCreate() {
        mode = "Create";
        this.initProgramme();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/programme/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit(Programme p) {
        this.programme = p;
        if (programme != null) {
            institution = p.getIdinstitution();
            axestrategique = p.getIdaxestrategique();
            soussecteur = p.getIdsoussecteur();
            mode = "Edit";
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/programme/Ajout.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/programme/programme.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                programme.setIdprogramme(programmeFacadeLocal.nextId());
                programme.setIdaxestrategique(axestrategique);
                programme.setIdinstitution(institution);
                programme.setIdsoussecteur(soussecteur);

                programme.setDateEnregistre(new Date());
                programme.setDerniereModif(new Date());
                programme.setEtat("Actif");

                programmeFacadeLocal.create(programme);
                this.initProgramme();

                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (programme != null) {
                    if (axestrategique.getIdaxestrategique() != null) {
                        programme.setIdaxestrategique(axestrategiqueFacadeLocal.find(axestrategique.getIdaxestrategique()));
                    }

                    if (institution.getIdinstitution() != null) {
                        programme.setIdinstitution(institutionFacadeLocal.find(institution.getIdinstitution()));
                    }

                    if (soussecteur.getIdsoussecteur() != null) {
                        programme.setIdsoussecteur(soussecteurFacadeLocal.find(soussecteur.getIdsoussecteur()));
                    }

                    programmeFacadeLocal.edit(programme);

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
            if (programme != null) {
                programmeFacadeLocal.remove(programme);
                programme = new Programme();
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Programme p) {
        try {
            if (p != null) {
                programmeFacadeLocal.remove(p);
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
