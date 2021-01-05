/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author USER
 */
@Entity
public class Bailleurfondoriginefinancement implements Serializable {

    @Id
    private Integer idbailleurfondoriginefinancement;
    @ManyToOne
    @JoinColumn(name = "idbailleurfond", referencedColumnName = "idbailleurfond")
    private Bailleurfond idbailleurfond;
    @ManyToOne
    @JoinColumn(name = "idoriginefinancement", referencedColumnName = "idoriginefinancement")
    private Originefinancement idoriginefinancement;
    private double pourcentage;
    private double montant;
    private double montantinitial;
    private double montantprogramme;
    private double montantdisponible;

    public Bailleurfondoriginefinancement() {
    }

    public Bailleurfondoriginefinancement(Integer idbailleurfondoriginefinancement) {
        this.idbailleurfondoriginefinancement = idbailleurfondoriginefinancement;
    }

    public Integer getIdbailleurfondoriginefinancement() {
        return idbailleurfondoriginefinancement;
    }

    public void setIdbailleurfondoriginefinancement(Integer idbailleurfondoriginefinancement) {
        this.idbailleurfondoriginefinancement = idbailleurfondoriginefinancement;
    }

    public Bailleurfond getIdbailleurfond() {
        return idbailleurfond;
    }

    public void setIdbailleurfond(Bailleurfond idbailleurfond) {
        this.idbailleurfond = idbailleurfond;
    }

    public Originefinancement getIdoriginefinancement() {
        return idoriginefinancement;
    }

    public void setIdoriginefinancement(Originefinancement idoriginefinancement) {
        this.idoriginefinancement = idoriginefinancement;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getMontantinitial() {
        return montantinitial;
    }

    public void setMontantinitial(double montantinitial) {
        this.montantinitial = montantinitial;
    }

    public double getMontantprogramme() {
        return montantprogramme;
    }

    public void setMontantprogramme(double montantprogramme) {
        this.montantprogramme = montantprogramme;
    }

    public double getMontantdisponible() {
        return montantdisponible;
    }

    public void setMontantdisponible(double montantdisponible) {
        this.montantdisponible = montantdisponible;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idbailleurfondoriginefinancement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bailleurfondoriginefinancement other = (Bailleurfondoriginefinancement) obj;
        if (!Objects.equals(this.idbailleurfondoriginefinancement, other.idbailleurfondoriginefinancement)) {
            return false;
        }
        return true;
    }

}
