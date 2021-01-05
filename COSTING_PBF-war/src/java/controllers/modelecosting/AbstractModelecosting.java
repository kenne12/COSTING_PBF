/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.modelecosting;

import entities.Imputation;
import entities.Modelecosting;
import entities.Unite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.ImputationFacadeLocal;
import sessions.ModelecostingFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.UniteFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractModelecosting {

    @EJB
    protected ModelecostingFacadeLocal modelecostingFacadeLocal;
    protected Modelecosting modelecosting = new Modelecosting();
    protected List<Modelecosting> modelecostings = new ArrayList<>();

    @EJB
    protected ImputationFacadeLocal imputationFacadeLocal;
    protected Imputation imputation = new Imputation();
    protected List<Imputation> imputations = new ArrayList<>();

    @EJB
    protected UniteFacadeLocal uniteFacadeLocal;
    protected Unite unite = new Unite();
    protected List<Unite> unites = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";
    protected boolean detail = true;

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
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

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Imputation getImputation() {
        return imputation;
    }

    public void setImputation(Imputation imputation) {
        this.imputation = imputation;
    }

    public List<Imputation> getImputations() {
        return imputations;
    }

    public Modelecosting getModelecosting() {
        return modelecosting;
    }

    public void setModelecosting(Modelecosting modelecosting) {
        this.modelecosting = modelecosting;
    }

    public List<Modelecosting> getModelecostings() {
        modelecostings = modelecostingFacadeLocal.findAllRange();
        return modelecostings;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public List<Unite> getUnites() {
        unites = uniteFacadeLocal.findAll();
        return unites;
    }

}
