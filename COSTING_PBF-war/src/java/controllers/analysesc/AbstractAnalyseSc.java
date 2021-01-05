/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.analysesc;

import entities.ContratMoyens;
import entities.Periode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;
import sessions.ContratMoyensFacadeLocal;
import sessions.PeriodeFacadeLocal;
import utils.AnalyseSCB;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractAnalyseSc {

    @EJB
    protected ContratMoyensFacadeLocal contratMoyensFacadeLocal;
    protected List<ContratMoyens> contratMoyenses = new ArrayList<>();

    protected PieChartModel pieChartModel;
    protected PieChartModel pieChartModel2;

    protected List<AnalyseSCB> analyses = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected Integer idPeriode;
    protected Periode periode = new Periode();
    protected List<Periode> periodes = new ArrayList<>();

    protected BarChartModel barModel;

    protected double montantTotalDisponible;
    protected double montantTotalProgramme;
    protected double ecart;

    protected Routine routine = new Routine();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public List<Periode> getPeriodes() {
        periodes = periodeFacadeLocal.findAllRange();
        return periodes;
    }

    public Integer getIdPeriode() {
        return idPeriode;
    }

    public void setIdPeriode(Integer idPeriode) {
        this.idPeriode = idPeriode;
    }

    public List<AnalyseSCB> getAnalyses() {
        return analyses;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }

    public PieChartModel getPieChartModel2() {
        return pieChartModel2;
    }

    public void setPieChartModel2(PieChartModel pieChartModel2) {
        this.pieChartModel2 = pieChartModel2;
    }

    public double getMontantTotalDisponible() {
        return montantTotalDisponible;
    }

    public double getMontantTotalProgramme() {
        return montantTotalProgramme;
    }

    public double getEcart() {
        return ecart;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

}
