/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.paragraphe;

import entities.Imputation;
import entities.Origine;
import entities.Sousrubrique;
import entities.Unite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.ImputationFacadeLocal;
import sessions.OrigineFacadeLocal;
import sessions.SousrubriqueFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.UniteFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractParagraphe {

    @EJB
    protected ImputationFacadeLocal imputationFacadeLocal;
    protected Imputation imputation = new Imputation();
    protected List<Imputation> imputations = new ArrayList<>();

    @EJB
    protected OrigineFacadeLocal origineFacadeLocal;
    protected Origine origine = new Origine();
    protected List<Origine> origines = new ArrayList<>();

    @EJB
    protected SousrubriqueFacadeLocal sousrubriqueFacadeLocal;
    protected Sousrubrique sousrubrique = new Sousrubrique();
    protected List<Sousrubrique> sousrubriques = new ArrayList<>();

    @EJB
    protected UniteFacadeLocal uniteFacadeLocal;
    protected List<Unite> unites = new ArrayList<>();
    protected Routine routine = new Routine();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public String getMode() {
        return mode;
    }

    public Routine getRoutine() {
        return routine;
    }

    public Imputation getImputation() {
        return imputation;
    }

    public void setImputation(Imputation imputation) {
        this.imputation = imputation;
    }

    public List<Imputation> getImputations() {
        imputations = imputationFacadeLocal.findAllOrder();
        return imputations;
    }

    public Origine getOrigine() {
        return origine;
    }

    public void setOrigine(Origine origine) {
        this.origine = origine;
    }

    public List<Origine> getOrigines() {
        origines = origineFacadeLocal.findAll();
        return origines;
    }

    public Sousrubrique getSousrubrique() {
        return sousrubrique;
    }

    public void setSousrubrique(Sousrubrique sousrubrique) {
        this.sousrubrique = sousrubrique;
    }

    public List<Sousrubrique> getSousrubriques() {
        sousrubriques = sousrubriqueFacadeLocal.findAll();
        return sousrubriques;
    }

    public List<Unite> getUnites() {
        unites = uniteFacadeLocal.findAll();
        return unites;
    }

}
