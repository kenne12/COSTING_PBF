/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.annexe;

import entities.Contrat;
import entities.Periode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.ContratFacadeLocal;
import sessions.PeriodeFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractImpressionAnnexe {

    @EJB
    protected ContratFacadeLocal contratFacadeLocal;
    protected Contrat contrat = new Contrat();
    protected List<Contrat> contrats = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected Integer idPeriode;
    protected List<Periode> periodes = new ArrayList<>();

    protected double montantTotal;

    protected Routine routine = new Routine();

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public List<Periode> getPeriodes() {
        periodes = periodeFacadeLocal.findAllRange();
        return periodes;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public Integer getIdPeriode() {
        return idPeriode;
    }

    public void setIdPeriode(Integer idPeriode) {
        this.idPeriode = idPeriode;
    }

}
