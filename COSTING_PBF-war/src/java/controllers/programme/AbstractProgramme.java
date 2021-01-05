/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.programme;

import controllers.util.SessionMBean;
import entities.Axestrategique;
import entities.Institution;
import entities.Programme;
import entities.Soussecteur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.AxestrategiqueFacadeLocal;
import sessions.InstitutionFacadeLocal;
import sessions.ProgrammeFacadeLocal;
import sessions.SoussecteurFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractProgramme {

    @EJB
    protected ProgrammeFacadeLocal programmeFacadeLocal;
    protected Programme programme = new Programme();
    protected List<Programme> programmes = new ArrayList<>();

    @EJB
    protected AxestrategiqueFacadeLocal axestrategiqueFacadeLocal;
    protected Axestrategique axestrategique = new Axestrategique();
    protected List<Axestrategique> axestrategiques = new ArrayList<>();

    @EJB
    protected SoussecteurFacadeLocal soussecteurFacadeLocal;
    protected Soussecteur soussecteur = new Soussecteur();
    protected List<Soussecteur> soussecteurs = new ArrayList<>();

    @EJB
    protected InstitutionFacadeLocal institutionFacadeLocal;
    protected Institution institution = new Institution();
    protected List<Institution> institutions = new ArrayList<>();

    protected Routine routine = new Routine();

    public AbstractProgramme() {
    }

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public List<Programme> getProgrammes() {
        programmes = programmeFacadeLocal.findByIdinstitution(SessionMBean.getInstitution().getIdinstitution());
        return programmes;
    }

    public void setProgrammes(List<Programme> programmes) {
        this.programmes = programmes;
    }

    public Axestrategique getAxestrategique() {
        return axestrategique;
    }

    public void setAxestrategique(Axestrategique axestrategique) {
        this.axestrategique = axestrategique;
    }

    public List<Axestrategique> getAxestrategiques() {
        axestrategiques = axestrategiqueFacadeLocal.findAll();
        return axestrategiques;
    }

    public void setAxestrategiques(List<Axestrategique> axestrategiques) {
        this.axestrategiques = axestrategiques;
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

    public void setSoussecteurs(List<Soussecteur> soussecteurs) {
        this.soussecteurs = soussecteurs;
    }

}
