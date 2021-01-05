/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Action;
import entities.Activite;
import entities.Bailleurfond;
import entities.Contrat;
import entities.Detailsc;
import entities.Evaluationstructure;
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
import java.util.Objects;
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
public class EvaluationController extends AbstractEvaluation implements Serializable {

    public EvaluationController() {

    }

    public void prepareCreate() {
        structure = new Structure();
        periode = new Periode();
        evaluationstructures.clear();
        listDetailsc.clear();
        scorePrec = 0;
        scorePrev = 0;
        scoreReal = 0;
        mode = "Create";
        message = "";
        tache = new Tache();
        taches.clear();
        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
    }

    public void prepareEdit(Structure s) {
        this.structure = s;
        if (structure != null) {
            mode = "Edit";
            message = "";
            this.updateData();
            this.sommeTotaux();
            RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
        }
    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Parametrage/critere/critere.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        evaluationstructures.clear();
        listDetailsc.clear();
        this.taches.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            if (periode.getIdperiode() != null && periode.getIdperiode() > 0) {

                Contrat c = contratFacadeLocal.findByIdtructureIdperiode(structure.getIdstructure(), SessionMBean.getBudget().getIdbudget(), periode.getIdperiode());
                if (c == null) {
                    JsfUtil.addWarningMessage("Aucun contrat créé à sur cette période");
                    return;
                }

                List<Evaluationstructure> list = evaluationstructureFacadeLocal.findByIdStructure(structure.getIdstructure(), SessionMBean.getBudget().getIdbudget(), periode.getIdperiode());
                if (!list.isEmpty()) {
                    this.evaluationstructures.addAll(list);
                } else {
                    List<Detailsc> listDetailSc = detailscFacadeLocal.findByIdStructure(structure.getIdstructure());
                    if (!listDetailSc.isEmpty()) {
                        for (Detailsc dsc : listDetailSc) {
                            Evaluationstructure e = new Evaluationstructure();
                            e.setIdevaluationstructure(0l);
                            e.setIdbudget(SessionMBean.getBudget());
                            e.setIddetailsc(dsc);
                            e.setIdperiode(periode);
                            e.setIdstructure(structure);
                            e.setScorePeriodeActuelPrevi(0d);
                            e.setScorePeriodeActuelReal(0d);
                            e.setScorePeriodePrec(0d);
                            e.setApplicable(true);
                            e.setProbleme("-");
                            this.evaluationstructures.add(e);
                        }
                    } else {
                        JsfUtil.addErrorMessage("Veuillez définir les criteres pour cette structure");
                    }
                }
                this.sommeTotaux();
                this.taches = tacheFacadeLocal.findByIdStructureIdAnneIdPeriode(structure.getIdstructure(), SessionMBean.getAnnee().getIdannee(), periode.getIdperiode());
            }
        }
    }

    public void removeCritere(Evaluationstructure item) {
        if (item.getIdevaluationstructure() != 0 && item.getIdevaluationstructure() != null) {
            evaluationstructures.remove(item);
            evaluationstructures.remove(item);
        } else {
            int conteur = 0;
            for (Evaluationstructure evs : evaluationstructures) {
                if (Objects.equals(item.getIddetailsc(), evs.getIddetailsc())) {
                    break;
                }
                conteur++;
            }
            evaluationstructures.remove(conteur);
        }
        this.sommeTotaux();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    private void initTache() {
        programme = new Programme();
        action = new Action();
        activite = new Activite();
        this.activites.clear();
        this.actions.clear();
        tache = new Tache();
        tache.setResultatattendu("-");
        tache.setNom("-");
        tache.setNumordre(0);
        bailleurfond = new Bailleurfond(1l);
        risque = new Risque(1l);
    }

    public void prepareAddTache(Evaluationstructure item) {
        this.evaluationstructure = item;
        tache = new Tache();
        this.initTache();
        RequestContext.getCurrentInstance().execute("PF('TacheCreateDialog').show()");
    }

    public void addTache() {
        if (programme.getIdprogramme() != null) {
            if (action.getIdaction() != null) {
                if (activite.getIdactivite() != null) {
                    Evaluationstructure e = evaluationstructure;
                    Activite a = this.activite;
                    Bailleurfond b = this.bailleurfond;
                    Risque r = this.risque;
                    Tache t = new Tache();
                    t.setIdtache(0l);
                    t.setIdactivite(a);
                    t.setIdnatureT(new NatureT(1));
                    t.setIdnaturetache(new Naturetache(1));
                    t.setIdtypefinancement(new Typefinancement(1));
                    t.setNom(tache.getNom());
                    t.setResultatattendu(tache.getResultatattendu());
                    t.setIdannee(SessionMBean.getAnnee());
                    t.setIdbudget(SessionMBean.getBudget());
                    t.setNumordre(0);
                    t.setEtat("Actif");
                    t.setDateEnregistre(new Date());
                    t.setDerniereModif(new Date());
                    t.setIdbailleurfond(bailleurfondFacadeLocal.find(b.getIdbailleurfond()));
                    t.setIdrisque(risqueFacadeLocal.find(r.getIdrisque()));
                    t.setIdevaluationstructure(e);
                    t.setMontantengage(0d);
                    t.setMontantliquide(0d);
                    t.setMontantpayeht(0d);
                    t.setMontantpayettc(0d);
                    t.setCpconsommee(0d);
                    t.setAeencours(0d);
                    t.setValider(true);
                    t.setTotalmontantaloue(0d);
                    t.setMoyensnecessaires("-");
                    t.setPeriodeexecution("-");
                    taches.add(t);

                    this.initTache();
                    JsfUtil.addSuccessMessage("Tache ajouté avec succès");
                }
            }
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

    public void save() {
        try {
            if (evaluationstructures.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            boolean erreur = verifyQuantite();
            if (erreur) {
                JsfUtil.addErrorMessage(message);
                return;
            }

            for (Evaluationstructure evs : evaluationstructures) {
                if (evs.getIdevaluationstructure() == 0l) {
                    evs.setIdevaluationstructure(evaluationstructureFacadeLocal.nextId());
                    evaluationstructureFacadeLocal.create(evs);
                    for (Tache t : taches) {
                        if (t.getIdevaluationstructure().getIddetailsc().equals(evs.getIddetailsc())) {
                            if (t.getIdtache() == 0l) {
                                t.setIdevaluationstructure(evs);
                                t.setIdstructure(structure);
                                t.setIdtache(tacheFacadeLocal.nextId());
                                this.initMois(t, periode.getIdperiode());
                                tacheFacadeLocal.create(t);
                            } else {
                                tacheFacadeLocal.edit(t);
                            }
                        }
                    }
                } else {
                    evaluationstructureFacadeLocal.edit(evs);
                    for (Tache t : taches) {
                        if (t.getIdevaluationstructure().getIddetailsc().equals(evs.getIddetailsc())) {
                            if (t.getIdtache() == 0l) {
                                t.setIdevaluationstructure(evs);
                                t.setIdtache(tacheFacadeLocal.nextId());
                                t.setIdstructure(structure);
                                this.initMois(t, periode.getIdperiode());
                                tacheFacadeLocal.create(t);
                            } else {
                                tacheFacadeLocal.edit(t);
                            }
                        }
                    }
                }
            }
            this.sommeTotaux();
            RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private void sommeTotaux() {
        scorePrec = 0;
        scorePrev = 0;
        scoreReal = 0;
        if (evaluationstructures.isEmpty()) {
            return;
        }

        for (Evaluationstructure evs : evaluationstructures) {
            scorePrec += evs.getScorePeriodePrec();
            scorePrev += evs.getScorePeriodeActuelPrevi();
            scoreReal += evs.getScorePeriodeActuelReal();
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
        } else {
            t.setM7(true);
            t.setM8(true);
            t.setM9(true);
            t.setM10(true);
            t.setM11(true);
            t.setM12(true);
        }
    }

    private boolean verifyQuantite() {
        boolean result = false;
        int i = 0;
        for (Evaluationstructure evs : evaluationstructures) {
            if ((evs.getScorePeriodePrec() > evs.getIddetailsc().getPointMax()) || (evs.getScorePeriodeActuelPrevi() > evs.getIddetailsc().getPointMax()) || (evs.getScorePeriodeActuelReal() > evs.getIddetailsc().getPointMax())) {
                result = true;
                message = "Vérifiez la saisie à la lgine  : " + (i + 1);
                break;
            }
            i++;
        }
        return result;
    }

}
