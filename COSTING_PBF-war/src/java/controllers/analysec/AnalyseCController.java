/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.analysec;

import controllers.util.SessionMBean;
import entities.ContratMoyens;
import entities.Programme;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import utils.AnalyseSCB;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class AnalyseCController extends AbstractAnalyseC implements Serializable {

    private void createBarModel() {
        this.barModel = initBarModel();

        this.barModel.setTitle("Diagramme comparatif des financement + " + this.periode.getNom());
        this.barModel.setLegendPosition("ne");

        Axis xAxis = this.barModel.getAxis(AxisType.X);
        xAxis.setLabel("Bailleur de fond");

        Axis yAxis = this.barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Montant");
        yAxis.setMin(0);
    }

    private void createPieCharModel() {
        this.pieChartModel = initPieCharModel();
    }

    private void createPieCharModel2() {
        this.pieChartModel2 = initPieCharModel2();
    }

    private BarChartModel initBarModel() {
        try {
            BarChartModel model = new BarChartModel();

            BarChartSeries series1 = new BarChartSeries();
            series1.setLabel("Montant disponible");

            BarChartSeries series2 = new BarChartSeries();
            series2.setLabel("Montant programmé");

            for (AnalyseSCB a : analyses) {
                series1.set(a.getProgramme().getCode(), a.getMontantDisponible());
                series2.set(a.getProgramme().getCode(), a.getMontantProgramme());
            }

            model.addSeries((ChartSeries) series1);
            model.addSeries((ChartSeries) series2);

            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return new BarChartModel();
        }
    }

    private PieChartModel initPieCharModel() {
        try {
            PieChartModel model = new PieChartModel();

            for (AnalyseSCB a : analyses) {
                model.set(a.getProgramme().getCode(), a.getMontantDisponible());
            }

            model.setTitle("Coubre des contributions par source de financement");
            model.setLegendPosition("w");
            model.setShadow(true);

            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return new PieChartModel();
        }
    }

    private PieChartModel initPieCharModel2() {
        try {
            PieChartModel model = new PieChartModel();

            for (AnalyseSCB a : analyses) {
                model.set(a.getProgramme().getCode(), a.getMontantDisponible());
            }

            model.setTitle("Coubre des contributions par source de financement");
            model.setLegendPosition("e");
            model.setShadow(false);
            model.setShowDataLabels(true);
            model.setDiameter(150);
            model.setFill(false);

            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return new PieChartModel();
        }
    }

    public AnalyseCController() {

    }

    @PostConstruct
    private void init() {
        idPeriode = 7;
        periode = periodeFacadeLocal.find(idPeriode);
        this.updateData();
    }

    public void updateData() {
        pieChartModel = new PieChartModel();
        barModel = new BarChartModel();
        pieChartModel2 = new PieChartModel();

        analyses.clear();
        contratMoyenses.clear();

        montantTotalDisponible = 0;
        montantTotalProgramme = 0;
        ecart = 0;
        List<Programme> programmes = new ArrayList<>();
        if (idPeriode != null && idPeriode > 0) {
            periode = periodeFacadeLocal.find(idPeriode);
            
            List<ContratMoyens> list = contratMoyensFacadeLocal.findByIdPeriodeFaEtatTache(idPeriode, SessionMBean.getInstitution().getIdinstitution(), SessionMBean.getBudget().getIdbudget() , true);
            if (!list.isEmpty()) {
                contratMoyenses.addAll(list);
                for (ContratMoyens c : list) {
                    if (!programmes.contains(c.getMoyens().getIdtache().getIdactivite().getIdaction().getIdprogramme())) {
                        programmes.add(c.getMoyens().getIdtache().getIdactivite().getIdaction().getIdprogramme());
                    }
                }
                int i = 0;
                for (Programme obj : programmes) {
                    i++;
                    AnalyseSCB a = new AnalyseSCB();
                    a.setId(i);
                    a.setProgramme(obj);
                    a.setMontantDisponible(obj.getMontantdisponible());
                    Map map = getData(obj, list);
                    double montant = (double) map.get("montant");
                    a.setMontantProgramme(montant);
                    a.setEcart(a.getMontantDisponible() - a.getMontantProgramme());
                    analyses.add(a);

                    List<ContratMoyens> cms = (List<ContratMoyens>) map.get("list");
                    list.removeAll(cms);

                    montantTotalDisponible += obj.getMontantdisponible();
                    montantTotalProgramme += montant;
                    ecart += a.getEcart();
                }
                this.createBarModel();
                this.createPieCharModel();
                this.createPieCharModel2();
            }
        }
    }

    public Map getData(Programme p, List<ContratMoyens> list) {
        Map map = new HashMap();
        List<ContratMoyens> cms = new ArrayList<>();
        double montant = 0;
        for (ContratMoyens c : list) {
            if (c.getMoyens().getIdtache().getIdactivite().getIdaction().getIdprogramme().equals(p)) {
                montant += c.getCt();
                cms.add(c);
            }
        }
        map.put("list", cms);
        map.put("montant", montant);
        return map;
    }

}
