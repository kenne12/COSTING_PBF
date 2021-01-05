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

/**
 *
 * @author USER
 */
@Entity
public class Originefinancement implements Serializable {

    @Id
    private Integer idoriginefinancement;
    private String nom;
    private String code;
    private double montantdisponible;

    public Originefinancement() {
    }

    public Originefinancement(Integer idoriginefinancement) {
        this.idoriginefinancement = idoriginefinancement;
    }

    public Integer getIdoriginefinancement() {
        return idoriginefinancement;
    }

    public void setIdoriginefinancement(Integer idoriginefinancement) {
        this.idoriginefinancement = idoriginefinancement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        hash = 71 * hash + Objects.hashCode(this.idoriginefinancement);
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
        final Originefinancement other = (Originefinancement) obj;
        if (!Objects.equals(this.idoriginefinancement, other.idoriginefinancement)) {
            return false;
        }
        return true;
    }

}
