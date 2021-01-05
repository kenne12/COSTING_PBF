/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.tache;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Action;
import entities.Activite;
import entities.Bailleurfond;
import entities.Evaluationstructure;
import entities.Imputation;
import entities.Moyens;
import entities.NatureT;
import entities.Naturetache;
import entities.Periode;
import entities.Programme;
import entities.Risque;
import entities.Structure;
import entities.Tache;
import entities.Typefinancement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class TacheController extends AbstractTache implements Serializable {

    @PostConstruct
    public void loadTache() {
        taches = tacheFacadeLocal.findByIdInstitution(SessionMBean.getInstitution().getIdinstitution(), SessionMBean.getAnnee().getIdannee());
    }

    public TacheController() {

    }

    private void initTache() {
        programme = new Programme();
        action = new Action();
        activite = new Activite();
        tache = new Tache();
        structure = new Structure();
        bailleurfond = new Bailleurfond(1l);
        risque = new Risque(1l);
        tache.setMontantengage(0d);
        tache.setMontantliquide(0d);
        tache.setMontantpayeht(0d);
        tache.setMontantpayettc(0d);
        tache.setMoyensnecessaires("-");
        tache.setTotalmontantaloue(0d);
        tache.setAeencours(0d);
        tache.setCpconsommee(0d);

        this.initChonogramme(tache);

        tache.setIdnatureT(new NatureT(1));
        tache.setIdtypefinancement(new Typefinancement(1));
        tache.setPeriodeexecution("-");
        tache.setNumordre(0);
        listMoyens.clear();

        this.initParagraphe();
    }

    public void prepareCreate() {
        mode = "Create";
        mode_paragraphe = "Create";
        this.initTache();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/tache/Ajout.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        if (tache != null) {
            action = activite.getIdaction();
            structure = activite.getIdstructure();
            activite = tache.getIdactivite();
            bailleurfond = tache.getIdbailleurfond();
            risque = tache.getIdrisque();
            structure = tache.getIdactivite().getIdstructure();
            programme = tache.getIdactivite().getIdaction().getIdprogramme();
            listMoyens = moyensFacadeLocal.findByTache(tache.getIdtache());
            evaluationstructure = new Evaluationstructure();
            periode = new Periode();
            this.filtreActionByProgram();
            this.filtreActiviteByAction();

            mode = "Edit";
            mode_paragraphe = "Create";
        }
    }

    public void prepareEdit(Tache t) {
        this.tache = t;
        if (tache != null) {
            action = t.getIdactivite().getIdaction();
            try {
                if (tache.getIdstructure() != null) {
                    structure = t.getIdstructure();
                }
            } catch (Exception e) {
                structure = new Structure();
            }
            programme = t.getIdactivite().getIdaction().getIdprogramme();
            activite = t.getIdactivite();
            bailleurfond = t.getIdbailleurfond();
            risque = t.getIdrisque();
            listMoyens = moyensFacadeLocal.findByTache(t.getIdtache());

            try {
                evaluationstructure = t.getIdevaluationstructure();
                periode = t.getIdevaluationstructure().getIdperiode();
            } catch (Exception e) {
                e.printStackTrace();
                evaluationstructure = new Evaluationstructure();
            }
            evaluationstructures = evaluationstructureFacadeLocal.findByIdStructure(structure.getIdstructure(), SessionMBean.getBudget().getIdbudget(), periode.getIdperiode());
            this.filtreActionByProgram();
            this.filtreActiviteByAction();
            this.initParagraphe();
            mode = "Edit";
            mode_paragraphe = "Create";

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/tache/Ajout.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/tache/tache.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filtreActionByProgram() {
        try {
            this.actions.clear();
            if (programme.getIdprogramme() != null) {
                actions = actionFacadeLocal.findByProgramme(programme.getIdprogramme());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filtreActiviteByAction() {
        try {
            this.activites.clear();
            if (action.getIdaction() != null) {
                activites = activiteFacadeLocal.findByAction(action.getIdaction());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filtreActivite() {
        taches.clear();
        try {
            if (structure.getIdstructure() > 0 && structure.getIdstructure() != null) {
                if (periode.getIdperiode() > 0 && structure.getIdstructure() != null) {
                    taches = tacheFacadeLocal.findByIdStructureIdAnneIdPeriode(structure.getIdstructure(), SessionMBean.getAnnee().getIdannee(), periode.getIdperiode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initParagraphe() {
        imputation = new Imputation();
        moyens = new Moyens();
        moyens.setCu(0d);
        moyens.setQte(0d);
        moyens.setCt(0d);
        moyens.setQuantite(0);
        moyens.setObservation("-");
        moyens.setNumero(0);
    }

    public void updateStrategie() {
        activite.setObjectif("");
        try {
            if (activite.getIdactivite() != null) {
                activite = activiteFacadeLocal.find(activite.getIdactivite());
            }
        } catch (Exception e) {
        }
    }

    public void updateCu() {
        moyens.setCu(0d);
        if (imputation.getIdimputation() != null) {
            imputation = imputationFacadeLocal.find(imputation.getIdimputation());
            if (imputation.getIdimputation() != null) {
                moyens.setCu(imputation.getCoutUnitaire());

                try {
                    if (moyens.getQte() != null) {
                        moyens.setCt(moyens.getCu() * moyens.getQte());
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void addParagraphe() {
        try {
            if (imputation.getIdimputation() == 0 || imputation.getIdimputation() == null) {
                JsfUtil.addErrorMessage("Veuillez sélectionner un paragraphe");
                return;
            }

            imputation = imputationFacadeLocal.find(imputation.getIdimputation());

            if ("Create".equals(mode_paragraphe)) {
                Moyens m = new Moyens();
                m = moyens;
                m.setIdimputation(imputation);
                m.setCt(m.getQte() * m.getCu());
                m.setQuantite(m.getQte().intValue());
                m.setIdmoyens(0l);
                m.setNumero(listMoyens.size() + 1);

                for (int i = 0; i < listMoyens.size(); i++) {
                    System.err.println(i);
                    listMoyens.get(i).setNumero((i + 1));
                }

                boolean drapeau = false;
                /*for (Moyens m1 : listMoyens) {
                 if (m1.getIdimputation().getIdimputation().equals(m.getIdimputation().getIdimputation())) {
                 drapeau = true;
                 break;
                 }
                 }*/

                if (!drapeau) {
                    listMoyens.add(m);
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.ajoute_avec_succes"));
                } else {
                    JsfUtil.addErrorMessage(routine.localizeMessage("notification.element_existant_dans_tableau"));
                    return;
                }

                total = calculTotal(listMoyens);
                tache.setTotalmontantaloue(total);

                this.initParagraphe();
                mode_paragraphe = "Create";
            } else {
                int i = 0;
                for (Moyens m1 : listMoyens) {
                    if (m1.getIdimputation().getIdimputation().equals(moyens.getIdimputation().getIdimputation())) {
                        listMoyens.set(i, moyens);
                        break;
                    }
                    i++;
                }

                total = calculTotal(listMoyens);
                tache.setTotalmontantaloue(total);
                mode_paragraphe = "Create";
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEditParagraphe(Moyens m) {
        imputation = m.getIdimputation();
        moyens = m;
        mode_paragraphe = "Edit";
    }

    public void updateProbleme() {
        try {
            evaluationstructures.clear();
            this.initChonogramme(tache);
            if (periode.getIdperiode() != null) {
                this.initMois(tache, periode.getIdperiode());
                if (structure.getIdstructure() != null) {
                    evaluationstructures = evaluationstructureFacadeLocal.findByIdStructure(structure.getIdstructure(), SessionMBean.getBudget().getIdbudget(), periode.getIdperiode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeParagraphe(Moyens moyens) {
        try {
            int i = 0;
            for (Moyens m : listMoyens) {
                if (m.getIdimputation().getIdimputation().equals(moyens.getIdimputation().getIdimputation()) && m.getNumero() == moyens.getNumero()) {
                    if (m.getIdmoyens() != 0l) {
                        moyensFacadeLocal.remove(moyens);
                        tache.setTotalmontantaloue(tache.getTotalmontantaloue() - m.getCt());
                        tacheFacadeLocal.edit(tache);
                    }
                    listMoyens.remove(i);
                    break;
                }
                i++;
            }
            total = calculTotal(listMoyens);
            this.tache.setTotalmontantaloue(total);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.retire_avec_succes"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMoyenLine() {
        try {
            if (moyens.getCu() != 0 && moyens.getCu() != null) {
                if (moyens.getQte() != 0 && moyens.getQte() != null) {
                    moyens.setCt(moyens.getQte() * moyens.getCu());
                    return;
                }
            }
            moyens.setCt(0d);
        } catch (Exception e) {
            e.printStackTrace();
            moyens.setCt(0d);
        }
    }

    public void updateTotal() {
        try {
            total = calculTotal(listMoyens);
            tache.setTotalmontantaloue(total);
            tache.setAeencours(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double calculTotal(List<Moyens> moyenses) {
        Double resultat = 0.0;
        if (!moyenses.isEmpty()) {
            for (Moyens m : moyenses) {
                resultat += m.getCu() * m.getQte();
            }
        }
        return resultat;
    }

    @Transactional
    public void save() {
        try {
            if ("Create".equals(mode)) {

                tache.setIdtache(tacheFacadeLocal.nextId());
                tache.setIdbailleurfond(bailleurfond);
                tache.setIdactivite(activite);
                tache.setIdrisque(risque);
                tache.setIdstructure(structure);

                tache.setIdannee(SessionMBean.getAnnee());
                tache.setIdtypefinancement(new Typefinancement(1));
                tache.setIdnatureT(new NatureT(1));
                tache.setIdnaturetache(new Naturetache(1));
                tache.setIdbudget(SessionMBean.getBudget());
                tache.setDateEnregistre(new Date());
                tache.setDerniereModif(new Date());
                tache.setValider(true);
                tache.setIdevaluationstructure(evaluationstructure);

                double montant = calculTotal(listMoyens);

                tache.setTotalmontantaloue(montant);
                tache.setCpconsommee(0d);
                tache.setEtat("Actif");
                tacheFacadeLocal.create(tache);
                int i = 0;
                for (Moyens m : listMoyens) {
                    Moyens m1 = m;
                    m1.setIdtache(tache);
                    m1.setIdmoyens(moyensFacadeLocal.nextId());
                    m1.setQuantite(m1.getQte().intValue());
                    m1.setMontantexecute(0d);
                    m1.setIdbudget(SessionMBean.getBudget());
                    m1.setNumero((i + 1));
                    moyensFacadeLocal.create(m1);
                    i++;
                }
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                taches = tacheFacadeLocal.findByIdInstitution(SessionMBean.getInstitution().getIdinstitution(), SessionMBean.getAnnee().getIdannee());
                this.initTache();
            } else if ("Edit".equals(mode)) {
                if (tache != null) {

                    if (bailleurfond.getIdbailleurfond() != null) {
                        tache.setIdbailleurfond(bailleurfondFacadeLocal.find(bailleurfond.getIdbailleurfond()));
                    }

                    if (risque.getIdrisque() != null) {
                        tache.setIdrisque(risqueFacadeLocal.find(risque.getIdrisque()));
                    }

                    if (activite.getIdactivite() != null) {
                        tache.setIdactivite(activiteFacadeLocal.find(activite.getIdactivite()));
                    }

                    if (evaluationstructure.getIdevaluationstructure() != null) {
                        tache.setIdevaluationstructure(evaluationstructureFacadeLocal.find(evaluationstructure.getIdevaluationstructure()));
                    }

                    //tache.setIdevaluationstructure(evaluationstructureFacadeLocal.find(evaluationstructure.getIdevaluationstructure()));
                    double montant = calculTotal(listMoyens);

                    tache.setTotalmontantaloue(montant);
                    tache.setIdstructure(structureFacadeLocal.find(structure.getIdstructure()));

                    tacheFacadeLocal.edit(tache);

                    for (Moyens m : listMoyens) {
                        Moyens m1 = m;
                        if (m1.getIdmoyens() != 0l) {
                            moyensFacadeLocal.edit(m1);
                        } else {
                            m1.setIdmoyens(moyensFacadeLocal.nextId());
                            m1.setIdtache(tache);
                            moyensFacadeLocal.create(m1);
                        }
                    }
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    taches = tacheFacadeLocal.findByIdInstitution(SessionMBean.getInstitution().getIdinstitution(), SessionMBean.getAnnee().getIdannee());
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne seletionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    @Transactional
    public void delete(Tache t) {
        try {
            moyensFacadeLocal.deleteByIdTache(t.getIdtache());
            tacheFacadeLocal.remove(t);
            tache = new Tache();
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private void initMois(Tache t, int idperiode) {
        if (idperiode == 1) {
            t.setM1(true);
            t.setM2(true);
            t.setM3(true);
        } else if (idperiode == 2) {
            t.setM4(true);
            t.setM5(true);
            t.setM6(true);
        } else if (idperiode == 3) {
            t.setM7(true);
            t.setM8(true);
            t.setM9(true);
        } else if (idperiode == 4) {
            t.setM10(true);
            t.setM11(true);
            t.setM12(true);
        } else if (idperiode == 5) {
            t.setM1(true);
            t.setM2(true);
            t.setM3(true);
            t.setM4(true);
            t.setM5(true);
            t.setM6(true);
        } else if (idperiode == 6) {
            t.setM7(true);
            t.setM8(true);
            t.setM9(true);
            t.setM10(true);
            t.setM11(true);
            t.setM12(true);
        } else {
            t.setM1(true);
            t.setM2(true);
            t.setM3(true);
            t.setM4(true);
            t.setM5(true);
            t.setM6(true);
            t.setM7(true);
            t.setM8(true);
            t.setM9(true);
            t.setM10(true);
            t.setM11(true);
            t.setM12(true);
        }
    }

    private void initChonogramme(Tache t) {
        t.setM1(false);
        t.setM2(false);
        t.setM3(false);
        t.setM4(false);
        t.setM5(false);
        t.setM6(false);
        t.setM7(false);
        t.setM8(false);
        t.setM9(false);
        t.setM10(false);
        t.setM11(false);
        t.setM12(false);
    }
}
