/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.bailleurfond;

import entities.Bailleurfond;
import entities.Sourcefinancement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.BailleurfondFacadeLocal;
import sessions.SourcefinancementFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractBailleurfond {

    @EJB
    protected BailleurfondFacadeLocal bailleurfondFacadeLocal;
    protected Bailleurfond bailleurfond = new Bailleurfond();
    protected List<Bailleurfond> bailleurfonds = new ArrayList<>();

    @EJB
    protected SourcefinancementFacadeLocal sourcefinancementFacadeLocal;
    protected Sourcefinancement sourcefinancement = new Sourcefinancement();
    protected List<Sourcefinancement> sourcefinancements = new ArrayList<>();

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

    public List<Bailleurfond> getBailleurfonds() {
        bailleurfonds = bailleurfondFacadeLocal.findAllRange();
        return bailleurfonds;
    }

    public Bailleurfond getBailleurfond() {
        return bailleurfond;
    }

    public void setBailleurfond(Bailleurfond bailleurfond) {
        this.bailleurfond = bailleurfond;
    }

    public Sourcefinancement getSourcefinancement() {
        return sourcefinancement;
    }

    public void setSourcefinancement(Sourcefinancement sourcefinancement) {
        this.sourcefinancement = sourcefinancement;
    }

    public List<Sourcefinancement> getSourcefinancements() {
        sourcefinancements = sourcefinancementFacadeLocal.findAll();
        return sourcefinancements;
    }

}
