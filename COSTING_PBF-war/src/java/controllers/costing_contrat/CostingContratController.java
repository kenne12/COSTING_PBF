/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.costing_contrat;

import controllers.util.JsfUtil;
import entities.Contrat;
import entities.ContratMoyens;
import entities.ContratMoyensPK;
import entities.CostingContratQte;
import entities.Moyens;
import entities.UniteCosting;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class CostingContratController extends AbstractCostingContrat implements Serializable {
    
    public CostingContratController() {
        
    }
    
    public void prepareEdit(Contrat c) {
        this.contrat = c;
        if (c != null) {
            mode = "Edit";
            selectedMoyens.clear();
            listMoyens.clear();
            structure = c.getIdstructure();
            contratTaches = contratTacheFacadeLocal.findByIdContrat(c.getIdcontrat(), true);
            
            contratMoyens = contratMoyensFacadeLocal.findByIdContrat(c.getIdcontrat());
            
            List<ContratMoyens> listInactifs = new ArrayList<>();
            costingContratQtesAll = costingContratQteFacadeLocal.findByIdContrat(c.getIdcontrat(), true);
            
            if (!contratMoyens.isEmpty()) {
                contratMoyens.forEach(cm -> {
                    selectedMoyens.add(cm.getMoyens());
                    if (!cm.isEtat()) {
                        listInactifs.add(cm);
                    }
                });
            }
            
            List<Moyens> list = new ArrayList<>();
            contratTaches.forEach(ct -> {
                list.addAll(moyensFacadeLocal.findByTache(ct.getTache().getIdtache()));
            });
            
            list.removeAll(selectedMoyens);
            listMoyens.addAll(list);
            
            periodes.clear();
            periodes.add(contrat.getIdperiode());
            selectedMoyens.clear();
            contratMoyens.removeAll(listInactifs);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/costing_contrat/Ajout.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void prepareEditCostingQte(ContratMoyens item) {
        this.costingContratQtes.clear();
        this.selectedUniteCostings.clear();
        
        this.contratMoyen = item;
        if (costingContratQtesAll.isEmpty()) {
            this.uniteCostings = uniteCostingFacadeLocal.findAll();
        } else {
            List<CostingContratQte> list = new ArrayList<>();
            for (CostingContratQte ccq : costingContratQtesAll) {
                if (item.getMoyens().getIdmoyens().equals(ccq.getIdmoyens().getIdmoyens())) {
                    list.add(ccq);
                    this.selectedUniteCostings.add(ccq.getIduniteCosting());
                }
            }
            this.costingContratQtes.addAll(list);
            this.uniteCostings = uniteCostingFacadeLocal.findAll();
            this.uniteCostings.removeAll(this.selectedUniteCostings);
        }
        RequestContext.getCurrentInstance().execute("PF('CostingQteEditDialog').show()");
    }
    
    public void addUniteToTable() {
        if (selectedUniteCostings.isEmpty()) {
            JsfUtil.addErrorMessage("common.tableau_vide");
            return;
        }
        for (UniteCosting uc : selectedUniteCostings) {
            if (!checkIfUnitExist(uc)) {
                CostingContratQte cp = new CostingContratQte();
                cp.setIdcostingContratQte(0l);
                cp.setIdcontrat(null);
                cp.setIduniteCosting(uc);
                cp.setQuantite(1d);
                cp.setIdmoyens(contratMoyen.getMoyens());
                costingContratQtes.add(cp);
                costingContratQtesAll.add(cp);
            }
        }
    }
    
    private void deleteUnite(CostingContratQte item, List<CostingContratQte> list) {
        int index = 0;
        for (CostingContratQte ccq : list) {
            if (ccq.getIduniteCosting().equals(item.getIduniteCosting())) {
                break;
            }
            index++;
        }
        
        if (item.getIdcostingContratQte() != 0l) {
            costingContratQteFacadeLocal.remove(item);
        }
        list.remove(index);
    }
    
    public void removeUnite(CostingContratQte item) {
        this.deleteUnite(item, costingContratQtes);
        this.deleteUnite(item, costingContratQtesAll);
        JsfUtil.addErrorMessage("notification.retire_avec_succes");
    }
    
    public boolean checkIfUnitExist(UniteCosting uc) {
        boolean result = false;
        for (CostingContratQte ccq : costingContratQtes) {
            if (ccq.getIduniteCosting().equals(uc)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public void editCostingQte() {
        if (costingContratQtes.isEmpty()) {
            JsfUtil.addErrorMessage("common.tableau_vide");
            return;
        }
        
        double sommeQte = 0;
        int i = 0;
        String observation = "";
        for (CostingContratQte cp : costingContratQtes) {
            if (i == 0) {
                if (cp.getQuantite() != null && cp.getQuantite() > 0) {
                    sommeQte = cp.getQuantite();
                    observation = cp.getQuantite() + " X " + cp.getIduniteCosting().getNom();
                }
            } else {
                if (cp.getQuantite() != null && cp.getQuantite() > 0) {
                    sommeQte = sommeQte * cp.getQuantite();
                    observation += " " + cp.getQuantite() + " X " + cp.getIduniteCosting().getNom();
                }
            }
            i++;
        }
        
        contratMoyen.setQuantite(sommeQte);
        contratMoyen.setObservationAuto(observation);
        contratMoyen.setCt((contratMoyen.getCu() * contratMoyen.getQuantite()));
        int index = returnIndexMoyens(contratMoyen);
        contratMoyens.set(index, contratMoyen);
        
        this.calculMontant();
        this.contratFacadeLocal.edit(contrat);
        RequestContext.getCurrentInstance().execute("PF('CostingQteEditDialog').hide()");
        JsfUtil.addErrorMessage(routine.localizeMessage("notification.operation_reussie"));
    }
    
    public void removeMoyens(ContratMoyens item) {
        try {
            int index = returnIndexMoyens(item);
            contratMoyens.remove(index);
            if (item.getContrat() != null) {
                contratMoyensFacadeLocal.remove(item);
                costingContratQteFacadeLocal.deleteByIdcontratIdMoyen(contrat.getIdcontrat(), item.getMoyens().getIdmoyens());
            }
            
            List<CostingContratQte> list = new ArrayList<>();
            list.addAll(costingContratQtesAll);
            int i = 0;
            for (CostingContratQte ccq : list) {
                if (ccq.getIdmoyens().equals(item.getMoyens())) {
                    costingContratQtesAll.remove(i);
                    if (ccq.getIdcostingContratQte() != 0l) {
                        costingContratQteFacadeLocal.remove(ccq);
                    }
                }
                i++;
            }
            listMoyens.add(item.getMoyens());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            JsfUtil.addFatalErrorMessage("Exception survénue");
            e.printStackTrace();
        }
    }
    
    private void calculMontant() {
        montantTotal = 0d;
        if (!this.contratMoyens.isEmpty()) {
            for (ContratMoyens cm : contratMoyens) {
                montantTotal += ((cm.getCu() * cm.getQuantite()));
            }
        }
        contrat.setMontant(montantTotal);
    }
    
    public int returnIndexMoyens(ContratMoyens item) {
        int i = 0;
        for (ContratMoyens cm : contratMoyens) {
            if (cm.getMoyens().equals(item.getMoyens())) {
                break;
            }
            i++;
        }
        return i;
    }
    
    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/costing_contrat/costing_contrat.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void filterContrat() {
        if (structure.getIdstructure() != null && structure.getIdstructure() != 0) {
            contrats = contratFacadeLocal.findByIdtructureIdBudget(structure.getIdstructure(), budget.getIdbudget());
        }
    }
    
    public void updateData() {
        try {
            listMoyens.clear();
            if (structure.getIdstructure() != null && structure.getIdstructure() != 0) {
                if (contrat.getIdperiode().getIdperiode() != null && contrat.getIdperiode().getIdperiode() != 0) {
                    structure = structureFacadeLocal.find(structure.getIdstructure());
                    
                    Contrat cTemp = contratFacadeLocal.findByIdtructureIdperiode(structure.getIdstructure(), budget.getIdbudget(), contrat.getIdperiode().getIdperiode());
                    if (cTemp != null) {
                        contratTaches = contratTacheFacadeLocal.findByIdContrat(cTemp.getIdcontrat(), true);
                        contratMoyens = contratMoyensFacadeLocal.findByIdContrat(cTemp.getIdcontrat(), true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addMoyenTotable() {
        try {
            if (selectedMoyens.isEmpty()) {
                JsfUtil.addErrorMessage("Aucun élément selectionné");
                return;
            }
            
            List<Moyens> list = new ArrayList<>();
            for (Moyens m : selectedMoyens) {
                if (!checkIfMoyensExistInList(m)) {
                    ContratMoyens cm = new ContratMoyens();
                    cm.setMoyens(m);
                    cm.setContrat(null);
                    cm.setCt(m.getCt());
                    cm.setCu(m.getCu());
                    cm.setObservation(m.getObservation());
                    cm.setObservationAuto("-");
                    cm.setQuantite(m.getQuantite().doubleValue());
                    cm.setEtat(true);
                    contratMoyens.add(cm);
                    list.add(m);
                }
            }
            this.sommeContrat();
            this.listMoyens.removeAll(list);
            selectedMoyens.removeAll(list);
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Verifier les données");
        }
    }
    
    private boolean checkIfMoyensExistInList(Moyens m) {
        if (contratMoyens.isEmpty()) {
            return false;
        }
        boolean result = false;
        for (ContratMoyens cm : contratMoyens) {
            if (cm.getMoyens().equals(m)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public List<CostingContratQte> returnListCosting(ContratMoyens item) {
        List<CostingContratQte> list = new ArrayList<>();
        for (CostingContratQte cp : costingContratQtesAll) {
            if (cp.getIdmoyens().equals(item.getMoyens())) {
                list.add(cp);
            }
        }
        return list;
    }
    
    @Transactional
    public void save() {
        try {
            if (contratMoyens.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }
            
            this.sommeContrat();
            
            if ("Edit".equals(mode)) {
                contratFacadeLocal.edit(contrat);
                
                for (ContratMoyens cm : contratMoyens) {
                    if (cm.getContrat() == null) {
                        cm.setContrat(contrat);
                        cm.setContratMoyensPK(new ContratMoyensPK(contrat.getIdcontrat(), cm.getMoyens().getIdmoyens()));
                        cm.setCt(cm.getCu() * cm.getQuantite());
                        contratMoyensFacadeLocal.create(cm);
                    } else {
                        cm.setCt(cm.getCu() * cm.getQuantite());
                        contratMoyensFacadeLocal.edit(cm);
                    }
                    
                    List<CostingContratQte> list = returnListCosting(cm);
                    
                    if (!list.isEmpty()) {
                        for (CostingContratQte item : list) {
                            if (item.getIdcontrat() == null) {
                                item.setIdcostingContratQte(costingContratQteFacadeLocal.nextId());
                                item.setIdcontrat(contrat);
                                costingContratQteFacadeLocal.create(item);
                            } else {
                                costingContratQteFacadeLocal.edit(item);
                            }
                        }
                    }
                }
                
                contratMoyens.clear();
                contratTaches.clear();
                costingContratQtes.clear();
                costingContratQtesAll.clear();
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/costing_contrat/costing_contrat.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
    private void sommeContrat() {
        Double resultat = 0d;
        for (ContratMoyens ct : contratMoyens) {
            resultat += (ct.getCu() * ct.getQuantite());
        }
        contrat.setMontant(resultat);
    }
    
    public String formatToStringMoney(Double montant) {
        double value = montant;
        return utilitaire.Utilitaires.formaterStringMoney(value);
    }
    
}
