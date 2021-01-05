/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exercice.findAll", query = "SELECT e FROM Exercice e"),
    @NamedQuery(name = "Exercice.findByIdexercice", query = "SELECT e FROM Exercice e WHERE e.idexercice = :idexercice"),
    @NamedQuery(name = "Exercice.findByCoutglobal", query = "SELECT e FROM Exercice e WHERE e.coutglobal = :coutglobal"),
    @NamedQuery(name = "Exercice.findByNomExercice", query = "SELECT e FROM Exercice e WHERE e.nomExercice = :nomExercice"),
    @NamedQuery(name = "Exercice.findByDateEnregistre", query = "SELECT e FROM Exercice e WHERE e.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Exercice.findByDerniereModif", query = "SELECT e FROM Exercice e WHERE e.derniereModif = :derniereModif"),
    @NamedQuery(name = "Exercice.findByEtat", query = "SELECT e FROM Exercice e WHERE e.etat = :etat"),
    @NamedQuery(name = "Exercice.findByMontantaccorde", query = "SELECT e FROM Exercice e WHERE e.montantaccorde = :montantaccorde"),
    @NamedQuery(name = "Exercice.findByIspublic", query = "SELECT e FROM Exercice e WHERE e.ispublic = :ispublic"),
    @NamedQuery(name = "Exercice.findByPropbudgetsante", query = "SELECT e FROM Exercice e WHERE e.propbudgetsante = :propbudgetsante"),
    @NamedQuery(name = "Exercice.findByMontantreel", query = "SELECT e FROM Exercice e WHERE e.montantreel = :montantreel")})
public class Exercice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idexercice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutglobal;
    @Size(max = 254)
    @Column(name = "nom_exercice")
    private String nomExercice;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @Size(max = 254)
    private String etat;
    private Double montantaccorde;
    private Boolean ispublic;
    private Double propbudgetsante;
    private Double montantreel;

    public Exercice() {
    }

    public Exercice(Integer idexercice) {
        this.idexercice = idexercice;
    }

    public Integer getIdexercice() {
        return idexercice;
    }

    public void setIdexercice(Integer idexercice) {
        this.idexercice = idexercice;
    }

    public Double getCoutglobal() {
        return coutglobal;
    }

    public void setCoutglobal(Double coutglobal) {
        this.coutglobal = coutglobal;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public Date getDateEnregistre() {
        return dateEnregistre;
    }

    public void setDateEnregistre(Date dateEnregistre) {
        this.dateEnregistre = dateEnregistre;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Double getMontantaccorde() {
        return montantaccorde;
    }

    public void setMontantaccorde(Double montantaccorde) {
        this.montantaccorde = montantaccorde;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    public Double getPropbudgetsante() {
        return propbudgetsante;
    }

    public void setPropbudgetsante(Double propbudgetsante) {
        this.propbudgetsante = propbudgetsante;
    }

    public Double getMontantreel() {
        return montantreel;
    }

    public void setMontantreel(Double montantreel) {
        this.montantreel = montantreel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexercice != null ? idexercice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exercice)) {
            return false;
        }
        Exercice other = (Exercice) object;
        if ((this.idexercice == null && other.idexercice != null) || (this.idexercice != null && !this.idexercice.equals(other.idexercice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Exercice[ idexercice=" + idexercice + " ]";
    }
    
}
