/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.compte_utilisateur;

import controllers.util.JsfUtil;
import entities.Compte;
import entities.Utilisateur;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utilitaire.ShaHash;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class CompteUtilisateurController extends AbstractCompteUtilisateur implements Serializable {

    public CompteUtilisateurController() {

    }

    public void prepareCreate() {
        utilisateur = new Utilisateur();
        compte = new Compte();
        mode = "Create";
        repeatPassword = "";
        this.filterUserWithoutAccount();
        RequestContext.getCurrentInstance().execute("PF('CompteUtilisateurCreateDialog').show()");
    }

    private void filterUserWithoutAccount() {
        try {
            List<Utilisateur> utAll = utilisateurFacadeLocal.findByEtat("Actif");
            utilisateurs.clear();
            for (Utilisateur ut : utAll) {
                if (compteFacadeLocal.findByIdutilisateur(ut.getIdUtilisateur()) == null) {
                    utilisateurs.add(ut);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        if (compte != null) {
            utilisateur = compte.getIdUtilisateur();
            utilisateurs.clear();
            utilisateurs.add(utilisateur);
            mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('CompteUtilisateurCreateDialog').show()");
        }
    }

    public void prepareEdit(Compte c) {
        this.compte = c;
        if (compte != null) {
            utilisateur = c.getIdUtilisateur();
            utilisateurs.clear();
            utilisateurs.add(c.getIdUtilisateur());
            mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('CompteUtilisateurCreateDialog').show()");
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                if (compte.getMdp().equals(repeatPassword)) {
                    compte.setIdCompte(compteFacadeLocal.nextId());
                    compte.setIdUtilisateur(utilisateur);
                    compte.setMdp(new ShaHash().hash(compte.getMdp()));
                    compte.setEtat("Actif");
                    compte.setDateEnregistre(new Date());
                    compte.setDernieremodif(new Date());
                    compte.setLangue("fr");
                    compte.setConnexion("false");
                    compteFacadeLocal.create(compte);

                    compte = new Compte();
                    utilisateur = new Utilisateur();
                    RequestContext.getCurrentInstance().execute("PF('CompteUtilisateurCreateDialog').hide()");
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    return;
                }
                JsfUtil.addWarningMessage("Les deux mot de passe ne sont pas identifiques");
            } else if ("Edit".equals(mode)) {
                if (compte != null) {
                    compteFacadeLocal.edit(compte);
                    utilisateur = new Utilisateur();
                    compte = new Compte();
                    RequestContext.getCurrentInstance().execute("PF('CompteUtilisateurCreateDialog').hide()");
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

    public void delete(Compte c) {
        try {
            compteFacadeLocal.remove(c);
            compte = new Compte();
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void changeStatus(Compte item, String action) {
        if (action.equals("desactiver")) {
            item.setEtat("Inactif");
        } else {
            item.setEtat("Actif");
        }
        compteFacadeLocal.edit(item);
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

}
