/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.compte_utilisateur;

import entities.Compte;
import entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CompteFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractCompteUtilisateur {

    @EJB
    protected UtilisateurFacadeLocal utilisateurFacadeLocal;
    protected Utilisateur utilisateur = new Utilisateur();
    protected List<Utilisateur> utilisateurs = new ArrayList<>();

    @EJB
    protected CompteFacadeLocal compteFacadeLocal;
    protected Compte compte = new Compte();
    protected List<Compte> comptes = new ArrayList<>();
    protected List<Compte> compteInactifs = new ArrayList<>();
    protected List<Compte> compteActifs = new ArrayList<>();
    protected String repeatPassword = "";

    protected Routine routine = new Routine();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public String getMode() {
        return mode;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public List<Compte> getComptes() {
        comptes = compteFacadeLocal.findAll();
        return comptes;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public List<Compte> getCompteInactifs() {
        compteInactifs = compteFacadeLocal.findByEtat("Inactif");
        return compteInactifs;
    }

    public List<Compte> getCompteActifs() {
        compteActifs = compteFacadeLocal.findByEtat("Actif");
        return compteActifs;
    }

}
