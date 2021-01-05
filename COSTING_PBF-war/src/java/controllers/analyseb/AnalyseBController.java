/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.analyseb;

import controllers.util.SessionMBean;
import entities.Bailleurfond;
import entities.Bailleurfondoriginefinancement;
import entities.ContratMoyens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class AnalyseBController extends AbstractAnalyseB implements Serializable {

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
                series1.set(a.getBailleurfondoriginefinancement().getIdoriginefinancement().getNom() + "_" + ((int) a.getBailleurfondoriginefinancement().getPourcentage()), a.getMontantDisponible());
                series2.set(a.getBailleurfondoriginefinancement().getIdoriginefinancement().getNom() + "_" + ((int) a.getBailleurfondoriginefinancement().getPourcentage()), a.getMontantProgramme());
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
                model.set(a.getBailleurfondoriginefinancement().getIdoriginefinancement().getNom() + "_" + ((int) a.getBailleurfondoriginefinancement().getPourcentage()), a.getMontantDisponible());
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
                model.set(a.getBailleurfondoriginefinancement().getIdoriginefinancement().getNom() + "_" + ((int) a.getBailleurfondoriginefinancement().getPourcentage()), a.getMontantDisponible());
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

    public AnalyseBController() {

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
        List<Bailleurfond> bailleurfonds = new ArrayList<>();
        if (idPeriode != null && idPeriode > 0) {
            periode = periodeFacadeLocal.find(idPeriode);
            List<ContratMoyens> list = contratMoyensFacadeLocal.findByIdPeriodeFbEtatTache(idPeriode, SessionMBean.getInstitution().getIdinstitution(), SessionMBean.getBudget().getIdbudget(), true);
            if (!list.isEmpty()) {
                contratMoyenses.addAll(list);
                for (ContratMoyens c : list) {
                    if (!bailleurfonds.contains(c.getMoyens().getIdtache().getIdbailleurfond())) {
                        bailleurfonds.add(c.getMoyens().getIdtache().getIdbailleurfond());
                    }
                }
                int i = 0;
                for (Bailleurfond b : bailleurfonds) {
                    i++;

                    List<Bailleurfondoriginefinancement> bfos = bailleurfondoriginefinancementFacadeLocal.findByIdBailleurfond(b.getIdbailleurfond());
                    Map map = getData(b, list);
                    double montant = (double) map.get("montant");

                    for (Bailleurfondoriginefinancement obj : bfos) {
                        i++;
                        AnalyseSCB a = new AnalyseSCB();
                        a.setId(i);
                        a.setBailleurfond(b);
                        a.setBailleurfondoriginefinancement(obj);
                        a.setMontantDisponible((obj.getIdoriginefinancement().getMontantdisponible() * obj.getPourcentage()) / 100);
                        a.setMontantProgramme((montant * obj.getPourcentage()) / 100);
                        a.setEcart(a.getMontantDisponible() - a.getMontantProgramme());
                        analyses.add(a);

                        montantTotalDisponible += a.getMontantDisponible();
                        montantTotalProgramme += a.getMontantProgramme();
                        ecart += a.getEcart();
                    }

                    List<ContratMoyens> cms = (List<ContratMoyens>) map.get("list");
                    list.removeAll(cms);
                }
                this.createBarModel();
                this.createPieCharModel();
                this.createPieCharModel2();
            }
        }
    }

    public Map getData(Bailleurfond b, List<ContratMoyens> list) {
        Map map = new HashMap();
        List<ContratMoyens> cms = new ArrayList<>();
        double montant = 0;
        for (ContratMoyens c : list) {
            if (c.getMoyens().getIdtache().getIdbailleurfond().equals(b)) {
                montant += c.getCt();
                cms.add(c);
            }
        }
        map.put("list", cms);
        map.put("montant", montant);
        return map;
    }

}
