/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.sous_critere;

import entities.Critere;
import entities.Souscritere;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CritereFacadeLocal;
import sessions.SouscritereFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractSousCritere {

    @EJB
    protected SouscritereFacadeLocal souscritereFacadeLocal;
    protected Souscritere souscritere = new Souscritere();
    protected List<Souscritere> souscriteres = new ArrayList<>();

    @EJB
    protected CritereFacadeLocal critereFacadeLocal;
    protected Critere critere = new Critere();
    protected List<Critere> criteres = new ArrayList<>();

    protected Routine routine = new Routine();

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    public List<Critere> getCriteres() {
        criteres = critereFacadeLocal.findAllRangeByCode();
        return criteres;
    }

    public Souscritere getSouscritere() {
        return souscritere;
    }

    public void setSouscritere(Souscritere souscritere) {
        this.souscritere = souscritere;
    }

    public List<Souscritere> getSouscriteres() {
        souscriteres = souscritereFacadeLocal.findAllRangeByCode();
        return souscriteres;
    }

}
