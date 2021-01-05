/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.plafond_budget;

import controllers.util.JsfUtil;
import entities.CostingPbQte;
import entities.CostingPbQtePK;
import entities.Modelecosting;
import entities.Plafondbudget;
import entities.Structure;
import entities.UniteCosting;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class PlafondbudgetController extends AbstractPlafondbudget implements Serializable {

    @PostConstruct
    private void init() {
        this.calculMontant();
    }

    public PlafondbudgetController() {

    }

    public void prepareCreate() {
        mode = "Create";
        this.initPlafond();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/plafond_budget/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        if (plafondbudget != null) {
            mode = "Edit";
        }
    }

    private void initPlafond() {
        structure = new Structure();
        message = "";
        plafondbudgets.clear();
        this.costingPbQtes.clear();
        this.costingPbQtesAll.clear();
        coefficient = 0;
        montantAnnuel = 0d;
        montantTrim = 0d;
    }

    public void prepareEdit(Plafondbudget item) {
        this.plafondbudget = item;
        mode = "Edit";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/costing/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/plafond_budget/plafond_budget.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        try {
            message = "";
            coefficient = 0;
            plafondbudgets.clear();
            costingPbQtes.clear();
            costingPbQtesAll.clear();
            if (structure.getIdstructure() != null) {
                structure = structureFacadeLocal.find(structure.getIdstructure());
                coefficient = structure.getIdtypestructure().getCoefficient();

                List<Plafondbudget> p = plafondbudgetFacadeLocal.findByIdstructureIdbudget(structure.getIdstructure(), budget.getIdbudget());
                if (!p.isEmpty()) {
                    plafondbudgets.addAll(p);
                    costingPbQtesAll.addAll(costingPbQteFacadeLocal.findByIdStructureIdBudget(structure.getIdstructure(), budget.getIdbudget()));
                } else {
                    List<Modelecosting> list = modelecostingFacadeLocal.findAllRange();
                    for (Modelecosting m : list) {
                        Plafondbudget pb = new Plafondbudget();
                        pb.setIdplafondbudget(0l);
                        pb.setIdbudget(budget);
                        pb.setIdmodelecosting(m);
                        pb.setCoefficient(coefficient);
                        pb.setCu(m.getCoutunitaire());
                        pb.setQuantite(m.getQuantite());
                        pb.setIdstructure(structure);
                        pb.setMontantannuel((pb.getCu() * pb.getQuantite()) * coefficient);
                        pb.setMontanttrim(pb.getMontantannuel() / 4);
                        plafondbudgets.add(pb);
                    }
                }
                this.calculMontant();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEditCostingQte(Plafondbudget item) {
        this.costingPbQtes.clear();
        this.selectedUniteCostings.clear();
        this.plafondbudget = item;
        if (costingPbQtesAll.isEmpty()) {
            this.uniteCostings = uniteCostingFacadeLocal.findAll();
        } else {
            List<CostingPbQte> list = new ArrayList<>();
            for (CostingPbQte cp : costingPbQtesAll) {
                if (cp.getPlafondbudget().getIdmodelecosting().equals(item.getIdmodelecosting())) {
                    list.add(cp);
                    this.selectedUniteCostings.add(cp.getUniteCosting());
                }
            }
            this.costingPbQtes.addAll(list);
            this.uniteCostings = uniteCostingFacadeLocal.findAll();
            this.uniteCostings.removeAll(this.selectedUniteCostings);
        }
        RequestContext.getCurrentInstance().execute("PF('CostingQteEditDialog').show()");
    }

    public void editCostingQte() {
        double sommeQte = 0;
        int i = 0;
        for (CostingPbQte cp : costingPbQtes) {
            if (i == 0) {
                if (cp.getQuantite() != null && cp.getQuantite() > 0) {
                    sommeQte = cp.getQuantite();
                }
            } else {
                if (cp.getQuantite() != null && cp.getQuantite() > 0) {
                    sommeQte = sommeQte * cp.getQuantite();
                }
            }
            i++;
        }
        plafondbudget.setQuantite(sommeQte);
        plafondbudget.setMontantannuel((plafondbudget.getCu() * plafondbudget.getQuantite()) * plafondbudget.getCoefficient());
        plafondbudget.setMontanttrim(plafondbudget.getMontantannuel() / 4);
        plafondbudgets.set(returnIndexPlafond(plafondbudget), plafondbudget);
        this.calculMontant();
        RequestContext.getCurrentInstance().execute("PF('CostingQteEditDialog').hide()");
        JsfUtil.addErrorMessage("Opération reussie");
    }

    public int returnIndexPlafond(Plafondbudget item) {
        int i = 0;
        for (Plafondbudget pb : plafondbudgets) {
            if (pb.getIdmodelecosting().equals(item.getIdmodelecosting())) {
                break;
            }
            i++;
        }
        return i;
    }

    public void addUniteToTable() {
        for (UniteCosting uc : selectedUniteCostings) {
            CostingPbQte cp = new CostingPbQte();
            cp.setPlafondbudget(plafondbudget);
            cp.setUniteCosting(uc);
            cp.setQuantite(1d);
            if (plafondbudget.getIdplafondbudget() != null && plafondbudget.getIdplafondbudget() > 0) {
                cp.setCostingPbQtePK(new CostingPbQtePK(plafondbudget.getIdplafondbudget(), uc.getIduniteCosting()));
            } else {
                cp.setCostingPbQtePK(new CostingPbQtePK(null, uc.getIduniteCosting()));
            }
            costingPbQtes.add(cp);
            costingPbQtesAll.add(cp);
        }
    }

    public void updateAmount() {
        if (structure.getIdstructure() == null || structure.getIdstructure() == 0) {
            coefficient = 0;
            return;
        }

        if (coefficient == null || coefficient == 0) {
            coefficient = structure.getIdtypestructure().getCoefficient();
        }

        int i = 0;
        for (Plafondbudget p : plafondbudgets) {
            p.setCoefficient(coefficient);
            p.setMontantannuel((p.getCu() * p.getQuantite()) * p.getCoefficient());
            p.setMontanttrim(p.getMontantannuel() / 4);
            plafondbudgets.set(i, p);
            i++;
        }
    }

    public List<CostingPbQte> returnListCosting(Plafondbudget item) {
        List<CostingPbQte> list = new ArrayList<>();
        for (CostingPbQte cp : costingPbQtesAll) {
            if (cp.getPlafondbudget().getIdmodelecosting().equals(item.getIdmodelecosting())) {
                list.add(cp);
            }
        }
        return list;
    }

    public void save() {
        try {
            if (plafondbudgets.isEmpty()) {
                message = routine.localizeMessage("common.tableau_vide");
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }
            for (Plafondbudget pb : plafondbudgets) {
                pb.setMontantannuel((pb.getCu() * pb.getQuantite()) * pb.getCoefficient());
                pb.setMontanttrim(pb.getMontantannuel() / 4);
                List<CostingPbQte> list = returnListCosting(pb);
                if (pb.getIdplafondbudget() == 0l) {
                    pb.setIdplafondbudget(plafondbudgetFacadeLocal.nextId());
                    plafondbudgetFacadeLocal.create(pb);
                    if (!list.isEmpty()) {
                        for (CostingPbQte cp : list) {
                            cp.getCostingPbQtePK().setIdplafondbudget(pb.getIdplafondbudget());
                            cp.setPlafondbudget(pb);
                            costingPbQteFacadeLocal.create(cp);
                        }
                    }
                } else {
                    plafondbudgetFacadeLocal.edit(pb);
                    if (!list.isEmpty()) {
                        for (CostingPbQte cp : list) {
                            CostingPbQte tmp = costingPbQteFacadeLocal.findByIdPlafondBudget(pb.getIdplafondbudget(), cp.getUniteCosting().getIduniteCosting());
                            if (tmp == null) {
                                cp.getCostingPbQtePK().setIdplafondbudget(pb.getIdplafondbudget());
                                cp.setPlafondbudget(pb);
                                costingPbQteFacadeLocal.create(cp);
                            } else {
                                costingPbQteFacadeLocal.edit(cp);
                            }
                        }
                    }
                }
            }
            this.calculMontant();
            this.initPlafond();
            detail = true;
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
            message = "";
        }
    }

    public void delete(Plafondbudget pb) {
        try {
            plafondbudgetFacadeLocal.remove(pb);
            if (plafondbudgets.contains(pb)) {
                plafondbudgets.remove(pb);
            }
            this.calculMontant();
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    private void calculMontant() {
        montantAnnuel = 0d;
        montantTrim = 0d;
        if (!this.plafondbudgets.isEmpty()) {
            for (Plafondbudget pb : plafondbudgets) {
                montantAnnuel += ((pb.getCu() * pb.getQuantite()) * pb.getCoefficient());
            }

            if (montantAnnuel > 0) {
                montantTrim = montantAnnuel / 4;
            }
        }
    }

    public String formatToStringMoney(Double montant) {
        return utilitaire.Utilitaires.formaterStringMoney(montant.intValue());
    }

}
