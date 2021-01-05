/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.utilisateur;

import controllers.util.JsfUtil;
import entities.Utilisateur;
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
public class UtilisateurController extends AbstractUtilisateur implements Serializable {

    public UtilisateurController() {

    }

    public void prepareCreate() {
        utilisateur = new Utilisateur();
        utilisateur.setCni("-");
        utilisateur.setDateNaissance(new Date());
        utilisateur.setPrenom("-");
        mode = "Create";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Securite/utilisateur/Ajout.html");
        } catch (Exception e) {
        }
    }

    public void prepareEdit() {
        if (utilisateur != null) {
            mode = "Edit";
        }
    }

    public void prepareEdit(Utilisateur u) {
        this.utilisateur = u;
        if (utilisateur != null) {
            mode = "Edit";
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Securite/utilisateur/Ajout.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Securite/utilisateur/utilisateur.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {

                utilisateur.setIdUtilisateur(utilisateurFacadeLocal.nextId());
                //utilisateur.setIdStatutMatrimonial(statutMatrimonial);

                //Adresse adresse = new Adresse();
                //long random_rue = (long) (Math.random() * rueFacadeLocal.findAll().size());
                //Rue r = rueFacadeLocal.find(1l);

                /*adresse.setIdAdresse(adresseFacadeLocal.nextId());
                 r.getIdQuartier().getIdVille();
                 adresse.setIdRue(r);
                 adresse.setIdQuartier(r.getIdQuartier());
                 adresse.setIdVille(r.getIdQuartier().getIdVille());
                 adresseFacadeLocal.create(adresse);

                 utilisateur.setIdPays(new Pays(1l));
                 utilisateur.setIdAdresse(adresse);*/
                utilisateur.setDateEnregistre(new Date());
                utilisateur.setDerniereModif(new Date());
                utilisateur.setDateNaissance(new Date());
                utilisateur.setEtat("Actif");
                utilisateur.setPhoto("-");
                utilisateur.setPhotoRelatif("-");
                utilisateurFacadeLocal.create(utilisateur);

                utilisateur = new Utilisateur();

                utilisateur = new Utilisateur();
                detail = true;
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (utilisateur != null) {
                    utilisateurFacadeLocal.edit(utilisateur);
                    utilisateur = new Utilisateur();
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

    public void delete(Utilisateur u) {
        try {
            if (u != null) {
                utilisateurFacadeLocal.remove(u);
                utilisateur = new Utilisateur();
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

}
