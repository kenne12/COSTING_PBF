/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.contrat;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Contrat;
import entities.ContratMoyens;
import entities.ContratMoyensPK;
import entities.ContratTache;
import entities.ContratTachePK;
import entities.Frequence;
import entities.Moyens;
import entities.Periode;
import entities.Structure;
import entities.Tache;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;
import utilitaire.Printer;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ContratController extends AbstractContrat implements Serializable {

    public ContratController() {

    }

    @PostConstruct
    private void init() {
        contrats = contratFacadeLocal.findByIdInstitutionIdBudget(SessionMBean.getInstitution().getIdinstitution(), SessionMBean.getBudget().getIdbudget());
    }

    public void prepareCreate() {
        mode = "Create";
        contrat.setIdperiode(new Periode());

        this.initContrat();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/contrat/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initContrat() {
        structure = new Structure();
        contrat = new Contrat();
        contrat.setIdfrequence(new Frequence());
        contrat.setIdperiode(new Periode());
        contrat.setDateSignature(new Date());
        contrat.setMontant(0d);
        taches.clear();
        selectedTaches.clear();
        contratMoyens.clear();
        contratTaches.clear();
        contrat.setDuree(0);
    }

    public void prepareEdit() {
        if (contrat != null) {
            mode = "Edit";
            selectedTaches.clear();

            structure = contrat.getIdstructure();
            contratTaches = contratTacheFacadeLocal.findByIdContrat(contrat.getIdcontrat());
            contratMoyens = contratMoyensFacadeLocal.findByIdContrat(contrat.getIdcontrat());
            taches = tacheFacadeLocal.findByIdStructureIdAnneIdPeriode(structure.getIdstructure(), budget.getIdbudget().longValue(), contrat.getIdperiode().getIdperiode());
            contratTaches.forEach(ct -> {
                selectedTaches.add(ct.getTache());
            });

            periodes.clear();
            periodes.add(contrat.getIdperiode());
        }
    }

    public void prepareEdit(Contrat c) {
        this.contrat = c;
        this.selectedTaches.clear();
        
            mode = "Edit";

            structure = c.getIdstructure();
            contratTaches = contratTacheFacadeLocal.findByIdContrat(c.getIdcontrat());
            contratMoyens = contratMoyensFacadeLocal.findByIdContrat(c.getIdcontrat());

            contratMoyensTrue = contratMoyensFacadeLocal.findByIdContrat(c.getIdcontrat(), true);

            contrat.setMontant(this.sommeContrat(contratMoyensTrue));

            taches = tacheFacadeLocal.findByIdStructureIdAnneIdPeriode(structure.getIdstructure(), SessionMBean.getAnnee().getIdannee(), contrat.getIdperiode().getIdperiode());
            contratTaches.forEach(ct -> {
                selectedTaches.add(ct.getTache());
            });

            periodes.clear();
            periodes.add(contrat.getIdperiode());
            taches.removeAll(selectedTaches);
            selectedTaches.clear();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/contrat/Ajout.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    private void forwordToIndex() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/contrat/contrat.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void forward() {
        this.forwordToIndex();
    }

    public void updatePeriode() {
        periodes.clear();
        if (contrat.getIdfrequence().getIdfrequence() != null && contrat.getIdfrequence().getIdfrequence() > 0) {
            periodes = periodeFacadeLocal.findByIdFrequence(contrat.getIdfrequence().getIdfrequence());
        }
    }

    public void filtreContrat() {
        contrats.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            contrats = contratFacadeLocal.findByIdtructureIdBudget(structure.getIdstructure(), SessionMBean.getBudget().getIdbudget());
        }
    }

    public void updateData() {
        try {
            listMoyens.clear();
            contrat.setClient("-");
            contrat.setExecutant("-");
            if (structure.getIdstructure() != null && structure.getIdstructure() != 0) {

                if (contrat.getIdperiode().getIdperiode() != null && contrat.getIdperiode().getIdperiode() != 0) {

                    structure = structureFacadeLocal.find(structure.getIdstructure());

                    try {
                        contrat.setClient(structure.getContractant());
                        contrat.setExecutant(structure.getContracteFullName());
                    } catch (Exception e) {
                    }

                    Contrat cTemp = contratFacadeLocal.findByIdtructureIdperiode(structure.getIdstructure(), budget.getIdbudget(), contrat.getIdperiode().getIdperiode());
                    if (cTemp != null) {
                        contratTaches = contratTacheFacadeLocal.findByIdContrat(cTemp.getIdcontrat());
                        contratMoyens = contratMoyensFacadeLocal.findByIdContrat(cTemp.getIdcontrat());
                    } else {
                        taches = tacheFacadeLocal.findByIdStructureIdAnneIdPeriode(structure.getIdstructure(), SessionMBean.getAnnee().getIdannee(), contrat.getIdperiode().getIdperiode());
                    }

                    int idP = contrat.getIdperiode().getIdperiode();
                    if (contrat.getIdperiode().getIdperiode() == 5 || contrat.getIdperiode().getIdperiode() == 6) {
                        contrat.setDuree(180);
                    } else if ((idP == 1 || idP == 2 || idP == 3 || idP == 4)) {
                        contrat.setDuree(90);
                    } else {
                        contrat.setDuree(365);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTacheTotable() {
        try {
            if (selectedTaches.isEmpty()) {
                JsfUtil.addErrorMessage("Aucun élément selectionné");
                return;
            }

            List<Tache> list = new ArrayList<>();

            for (Tache t : selectedTaches) {
                if (!checkIfTacheExistInList(t)) {
                    ContratTache ct = new ContratTache();
                    ct.setTache(t);
                    ct.setContrat(null);
                    ct.setEtat(true);
                    contratTaches.add(ct);
                    list.add(t);
                }
            }

            this.sommeContrat(contratMoyens);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.ajoute_avec_succes"));
            taches.removeAll(list);
            selectedTaches.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfTacheExistInList(Tache t) {
        if (contratTaches.isEmpty()) {
            return false;
        }
        boolean result = false;
        for (ContratTache ct : contratTaches) {
            if (ct.getTache().equals(t)) {
                result = true;
                break;
            }
        }
        return result;
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

    public void setEtatTache(ContratTache ct) {
        System.err.println("" + ct.isEtat());
    }

    public void removeTache(ContratTache ct) {
        try {
            int i = 0;
            for (ContratTache c : contratTaches) {
                if (c.getTache().getIdtache().equals(ct.getTache().getIdtache())) {
                    break;
                }
                i++;
            }
            contratTaches.remove(i);
            if (ct.getContrat() == null) {
                List<ContratMoyens> listToRemove = new ArrayList<>();

                int conteur = 0;
                for (ContratMoyens cm : contratMoyens) {
                    if (ct.getTache().getIdtache().equals(cm.getMoyens().getIdtache().getIdtache())) {
                        listToRemove.add(cm);
                    }
                    conteur++;
                }

                for (ContratMoyens cm : listToRemove) {
                    int i_1 = 0;
                    for (ContratMoyens cm1 : contratMoyens) {
                        if (cm.getMoyens().equals(cm1.getMoyens())) {
                            contratMoyens.remove(i_1);
                            break;
                        }
                        i_1++;
                    }
                }
            } else {
                contratTacheFacadeLocal.remove(ct);
                List<ContratMoyens> listToRemove = new ArrayList<>();
                for (ContratMoyens cm : contratMoyens) {
                    if (ct.getTache().getIdtache().equals(cm.getMoyens().getIdtache().getIdtache())) {
                        listToRemove.add(cm);
                    }
                }
                if (!listToRemove.isEmpty()) {
                    contratMoyens.removeAll(listToRemove);
                    for (ContratMoyens cm : listToRemove) {
                        contratMoyensFacadeLocal.remove(cm);
                    }
                }
            }
            this.sommeContrat(contratMoyens);
            taches.add(ct.getTache());
            JsfUtil.addSuccessMessage("notification.operation_reussie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void save() {
        try {
            /*if (contratTaches.isEmpty()) {
             JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
             return;
             }*/

            if ("Create".equals(mode)) {
                contrat.setMontant(this.sommeContrat(contratMoyensTrue));
                contrat.setIdcontrat(contratFacadeLocal.nextId());
                contrat.setIdbudget(budget);
                contrat.setIdstructure(structure);
                contrat.setDateSignature(new Date());
                contrat.setEtat(true);
                contratFacadeLocal.create(contrat);

                for (ContratTache ct : contratTaches) {
                    ContratTache ob = ct;
                    ob.setContrat(contrat);
                    ob.setContratTachePK(new ContratTachePK(ob.getTache().getIdtache(), contrat.getIdcontrat()));
                    initMoisByPeriod(ob, contrat.getIdperiode().getIdperiode());
                    contratTacheFacadeLocal.create(ob);
                }

                contrats = contratFacadeLocal.findByIdtructureIdBudget(structure.getIdstructure(), SessionMBean.getBudget().getIdbudget());
                this.initContrat();
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                contratFacadeLocal.edit(contrat);

                for (ContratTache ct : contratTaches) {
                    if (ct.getContrat() == null) {
                        ct.setContrat(contrat);
                        ct.setContratTachePK(new ContratTachePK(ct.getTache().getIdtache(), contrat.getIdcontrat()));
                        initMoisByPeriod(ct, contrat.getIdperiode().getIdperiode());
                        contratTacheFacadeLocal.create(ct);
                    } else {
                        ContratTache obj = contratTacheFacadeLocal.findByIdContratIdtache(contrat.getIdcontrat(), ct.getTache().getIdtache());
                        if (obj.isEtat() != ct.isEtat()) {
                            List<ContratMoyens> listM = contratMoyensFacadeLocal.findByIdTache(ct.getTache().getIdtache());
                            if (!listM.isEmpty()) {
                                for (ContratMoyens c : listM) {
                                    c.setEtat(ct.isEtat());
                                    contratMoyensFacadeLocal.edit(c);
                                }
                            }
                        }
                        contratTacheFacadeLocal.edit(ct);
                    }
                }

                contratMoyensTrue = contratMoyensFacadeLocal.findByIdContrat(contrat.getIdcontrat(), true);
                contrat.setMontant(this.sommeContrat(contratMoyensTrue));
                contratFacadeLocal.edit(contrat);

                if (structure.getIdstructure() != null) {
                    contrats = contratFacadeLocal.findByIdtructureIdBudget(structure.getIdstructure(), SessionMBean.getBudget().getIdbudget());
                }
                this.forwordToIndex();
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private void initMoisByPeriod(ContratTache ct, int idperiode) {
        if (idperiode == 1) {
            ct.setMois1("Janvier");
            ct.setMois2("Février");
            ct.setMois3("Mars");
        } else if (idperiode == 2) {
            ct.setMois1("Avril");
            ct.setMois2("Mai");
            ct.setMois3("Juin");
        } else if (idperiode == 3) {
            ct.setMois1("Juillet");
            ct.setMois2("Aout");
            ct.setMois3("Septembre");
        } else if (idperiode == 4) {
            ct.setMois1("Octobre");
            ct.setMois2("Novembre");
            ct.setMois3("Décembre");
        } else if (idperiode == 5) {
            ct.setMois1("Janvier");
            ct.setMois2("Février");
            ct.setMois3("Mars");
            ct.setMois1("Avril");
            ct.setMois2("Mai");
            ct.setMois3("Juin");
        } else {
            ct.setMois1("Juillet");
            ct.setMois2("Aout");
            ct.setMois3("Septembre");
            ct.setMois1("Octobre");
            ct.setMois2("Novembre");
            ct.setMois3("Décembre");
        }
    }

    @Transactional
    public void delete(Contrat c) {
        try {
            contratMoyensFacadeLocal.deleteByIdContrat(c.getIdcontrat());
            contratTacheFacadeLocal.deleteByIdContrat(c.getIdcontrat());
            contratFacadeLocal.remove(c);
            if (structure.getIdstructure() != null) {
                contrats = contratFacadeLocal.findByIdtructureIdBudget(structure.getIdstructure(), SessionMBean.getBudget().getIdbudget());
            } else {
                contrats = contratFacadeLocal.findByIdInstitutionIdBudget(SessionMBean.getInstitution().getIdinstitution(), SessionMBean.getBudget().getIdbudget());
            }
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Veuillez supprimer le costing lié au contrat");
        }
    }

    private Double sommeContrat(List<ContratMoyens> list) {
        Double resultat = 0d;
        for (ContratMoyens ct : list) {
            resultat += (ct.getCu() * ct.getQuantite());
        }
        return resultat;
    }

    public void printContrat(Contrat item, String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idcontrat", item.getIdcontrat());
            parameter.put("idbudget", item.getIdbudget().getIdbudget());
            parameter.put("idperiode", item.getIdperiode().getIdperiode());
            parameter.put("idstructure", item.getIdstructure().getIdstructure());
            Printer p = new Printer();
            if (option.equals("pdf")) {
                p.print("/report/contrat.jasper", parameter);
            } else {
                p.DOCX("/report/contrat.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAnnexe(long item, String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idcontrat", item);

            Printer p = new Printer();
            if (option.equals("pdf")) {
                p.print("/report/annexe.jasper", parameter);
            } else {
                p.DOCX("/report/annexe.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
