/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.institution;

import entities.Institution;
import entities.Soussecteur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.InstitutionFacadeLocal;
import sessions.SoussecteurFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractInstitution {

    @EJB
    protected InstitutionFacadeLocal institutionFacadeLocal;
    protected Institution institution = new Institution();
    protected List<Institution> institutions = new ArrayList<>();

    @EJB
    protected SoussecteurFacadeLocal soussecteurFacadeLocal;
    protected Soussecteur soussecteur = new Soussecteur();
    protected List<Soussecteur> soussecteurs = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<Institution> getInstitutions() {
        institutions = institutionFacadeLocal.findAllRange();
        return institutions;
    }

    public Soussecteur getSoussecteur() {
        return soussecteur;
    }

    public void setSoussecteur(Soussecteur soussecteur) {
        this.soussecteur = soussecteur;
    }

    public List<Soussecteur> getSoussecteurs() {
        soussecteurs = soussecteurFacadeLocal.findAll();
        return soussecteurs;
    }

}
