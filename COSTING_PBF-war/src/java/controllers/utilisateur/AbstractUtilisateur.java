/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.utilisateur;

import entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.StructureFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractUtilisateur {

    @EJB
    protected UtilisateurFacadeLocal utilisateurFacadeLocal;
    protected Utilisateur utilisateur = new Utilisateur();
    protected List<Utilisateur> utilisateurs = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";
    protected boolean detail = true;

    //@EJB
    //private AdresseFacadeLocal adresseFacadeLocal;
    //@EJB
    //private RueFacadeLocal rueFacadeLocal;
    //@EJB
    //private StatutMatrimonialFacadeLocal statutMatrimonialFacadeLocal;
    //private StatutMatrimonial statutMatrimonial = new StatutMatrimonial();
    //private List<StatutMatrimonial> statutMatrimonials = new ArrayList<>();
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        utilisateurs = utilisateurFacadeLocal.findAll();
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    /*public StatutMatrimonial getStatutMatrimonial() {
     return statutMatrimonial;
     }

     public void setStatutMatrimonial(StatutMatrimonial statutMatrimonial) {
     this.statutMatrimonial = statutMatrimonial;
     }

     public List<StatutMatrimonial> getStatutMatrimonials() {
     statutMatrimonials = statutMatrimonialFacadeLocal.findAll();
     return statutMatrimonials;
     }

     public void setStatutMatrimonials(List<StatutMatrimonial> statutMatrimonials) {
     this.statutMatrimonials = statutMatrimonials;
     }*/
    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

}
