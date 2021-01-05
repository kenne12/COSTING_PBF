/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.annexe;

import controllers.util.SessionMBean;
import entities.Contrat;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utilitaire.Printer;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ImpressionAnnexeController extends AbstractImpressionAnnexe implements Serializable {
    
    public ImpressionAnnexeController() {
        
    }
    
    @PostConstruct
    private void init() {
        
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
        if (idPeriode != null && idPeriode > 0) {
            contrats = contratFacadeLocal.findByIdInstitutionIdBudgetIdPeriode(SessionMBean.getInstitution().getIdinstitution(), SessionMBean.getBudget().getIdbudget(), idPeriode);
            this.sommeContrat();
        }
    }
    
    private void sommeContrat() {
        montantTotal = 0;
        for (Contrat ct : contrats) {
            montantTotal += ct.getMontant();
        }
    }
    
    public void printAnnexe3(String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idPeriode", idPeriode);
            parameter.put("idBudget", SessionMBean.getBudget().getIdbudget());
            parameter.put("idInstitution", SessionMBean.getInstitution().getIdinstitution());
            
            Printer p = new Printer();
            
            if (option.equals("pdf")) {
                p.print("/report/couverture_annexe_03.jasper", parameter);
            } else {
                p.DOCX("/report/couverture_annexe_03.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void printAnnexe2(String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idPeriode", idPeriode);
            parameter.put("idBudget", SessionMBean.getBudget().getIdbudget());
            parameter.put("idInstitution", SessionMBean.getInstitution().getIdinstitution());
            
            Printer p = new Printer();
            
            if (option.equals("pdf")) {
                p.print("/report/couverture_cadre_reference.jasper", parameter);
            } else {
                p.DOCX("/report/couverture_cadre_reference.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void printCosting(String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idPeriode", idPeriode);
            parameter.put("idBudget", SessionMBean.getBudget().getIdbudget());
            parameter.put("idInstitution", SessionMBean.getInstitution().getIdinstitution());
            
            Printer p = new Printer();
            
            if (option.equals("pdf")) {
                p.print("/report/tableau_costing_activite.jasper", parameter);
            } else {
                p.DOCX("/report/tableau_costing_activite.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void printChronogramme(String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idPeriode", idPeriode);
            parameter.put("idBudget", SessionMBean.getBudget().getIdbudget());
            parameter.put("idInstitution", SessionMBean.getInstitution().getIdinstitution());
            
            Printer p = new Printer();
            
            if (option.equals("pdf")) {
                p.print("/report/annexe_chronogramme_moe.jasper", parameter);
            } else {
                p.DOCX("/report/annexe_chronogramme_moe.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
