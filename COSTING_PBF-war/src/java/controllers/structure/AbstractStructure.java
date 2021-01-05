/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.structure;

import entities.Institution;
import entities.Pyramide;
import entities.Statutstructure;
import entities.Structure;
import entities.Typestructure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.InstitutionFacadeLocal;
import sessions.PyramideFacadeLocal;
import sessions.StatutstructureFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypestructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractStructure {

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected TypestructureFacadeLocal typestructureFacadeLocal;
    protected Typestructure typestructure = new Typestructure();
    protected List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    protected StatutstructureFacadeLocal statutstructureFacadeLocal;
    protected Statutstructure statutstructure = new Statutstructure();
    protected List<Statutstructure> statutstructures = new ArrayList<>();

    @EJB
    protected PyramideFacadeLocal pyramideFacadeLocal;
    protected Pyramide pyramide = new Pyramide();
    protected List<Pyramide> pyramides = new ArrayList<>();

    @EJB
    protected InstitutionFacadeLocal institutionFacadeLocal;
    protected Institution institution = new Institution();
    protected List<Institution> institutions = new ArrayList<>();

    protected Routine routine = new Routine();

    public AbstractStructure() {
    }

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

    public List<Structure> getStructures() {
        structures = structureFacadeLocal.findAllRange();
        return structures;
    }

    public List<Typestructure> getTypestructures() {
        typestructures = typestructureFacadeLocal.findAll();
        return typestructures;
    }

    public Statutstructure getStatutstructure() {
        return statutstructure;
    }

    public void setStatutstructure(Statutstructure statutstructure) {
        this.statutstructure = statutstructure;
    }

    public List<Statutstructure> getStatutstructures() {
        statutstructures = statutstructureFacadeLocal.findAll();
        return statutstructures;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public Pyramide getPyramide() {
        return pyramide;
    }

    public void setPyramide(Pyramide pyramide) {
        this.pyramide = pyramide;
    }

    public List<Pyramide> getPyramides() {
        pyramides = pyramideFacadeLocal.findAll();
        return pyramides;
    }

    public void setPyramides(List<Pyramide> pyramides) {
        this.pyramides = pyramides;
    }

}
