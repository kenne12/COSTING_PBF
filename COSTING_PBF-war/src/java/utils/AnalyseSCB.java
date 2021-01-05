/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Action;
import entities.Bailleurfond;
import entities.Bailleurfondoriginefinancement;
import entities.Programme;

/**
 *
 * @author USER
 */
public class AnalyseSCB {

    int id;
    private Bailleurfond bailleurfond;
    private Action action;
    private Programme programme;
    private Bailleurfondoriginefinancement bailleurfondoriginefinancement;

    private double montantProgramme;
    private double montantDisponible;
    private double ecart;

    public AnalyseSCB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontantProgramme() {
        return montantProgramme;
    }

    public void setMontantProgramme(double montantProgramme) {
        this.montantProgramme = montantProgramme;
    }

    public double getMontantDisponible() {
        return montantDisponible;
    }

    public void setMontantDisponible(double montantDisponible) {
        this.montantDisponible = montantDisponible;
    }

    public double getEcart() {
        return ecart;
    }

    public void setEcart(double ecart) {
        this.ecart = ecart;
    }

    public Bailleurfond getBailleurfond() {
        return bailleurfond;
    }

    public void setBailleurfond(Bailleurfond bailleurfond) {
        this.bailleurfond = bailleurfond;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public Bailleurfondoriginefinancement getBailleurfondoriginefinancement() {
        return bailleurfondoriginefinancement;
    }

    public void setBailleurfondoriginefinancement(Bailleurfondoriginefinancement bailleurfondoriginefinancement) {
        this.bailleurfondoriginefinancement = bailleurfondoriginefinancement;
    }

}
