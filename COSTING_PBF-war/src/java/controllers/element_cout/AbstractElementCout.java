/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.element_cout;

import entities.UniteCosting;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.UniteCostingFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractElementCout {

    @EJB
    protected UniteCostingFacadeLocal uniteCostingFacadeLocal;
    protected UniteCosting uniteCosting = new UniteCosting();
    protected List<UniteCosting> uniteCostings = new ArrayList<>();

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

    public UniteCosting getUniteCosting() {
        return uniteCosting;
    }

    public void setUniteCosting(UniteCosting uniteCosting) {
        this.uniteCosting = uniteCosting;
    }

    public List<UniteCosting> getUniteCostings() {
        uniteCostings = uniteCostingFacadeLocal.findAllRangeByCode();
        return uniteCostings;
    }

    public void setUniteCostings(List<UniteCosting> uniteCostings) {
        this.uniteCostings = uniteCostings;
    }

}
