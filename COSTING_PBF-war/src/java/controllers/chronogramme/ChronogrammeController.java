/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.chronogramme;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Moyens;
import entities.Tache;
import java.io.Serializable;
import java.util.List;
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
public class ChronogrammeController extends AbstractChronogramme implements Serializable {

    public ChronogrammeController() {

    }

    public void forward() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/Traitement/tache/tache.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filtreActivite() {
        taches.clear();
        try {
            if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
                if (periode.getIdperiode() != null && periode.getIdperiode() > 0) {
                    taches = tacheFacadeLocal.findByIdStructureIdAnneIdPeriode(structure.getIdstructure(), SessionMBean.getAnnee().getIdannee(), periode.getIdperiode());
                }
            }
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

            if (taches.isEmpty()) {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
                return;
            }

            for (Tache t : taches) {
                tacheFacadeLocal.edit(t);
            }
            taches = tacheFacadeLocal.findByIdStructureIdAnneIdPeriode(structure.getIdstructure(), SessionMBean.getAnnee().getIdannee(), periode.getIdperiode());
            JsfUtil.addSuccessMessage("Operation réussie");
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
