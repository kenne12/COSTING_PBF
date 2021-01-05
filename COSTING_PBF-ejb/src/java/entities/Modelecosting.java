/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelecosting.findAll", query = "SELECT m FROM Modelecosting m"),
    @NamedQuery(name = "Modelecosting.findByIdmodelecosting", query = "SELECT m FROM Modelecosting m WHERE m.idmodelecosting = :idmodelecosting"),
    @NamedQuery(name = "Modelecosting.findByQuantite", query = "SELECT m FROM Modelecosting m WHERE m.quantite = :quantite"),
    @NamedQuery(name = "Modelecosting.findByCoutunitaire", query = "SELECT m FROM Modelecosting m WHERE m.coutunitaire = :coutunitaire"),
    @NamedQuery(name = "Modelecosting.findByMontanttimestre", query = "SELECT m FROM Modelecosting m WHERE m.montanttimestre = :montanttimestre"),
    @NamedQuery(name = "Modelecosting.findByMontantannuel", query = "SELECT m FROM Modelecosting m WHERE m.montantannuel = :montantannuel")})
public class Modelecosting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idmodelecosting;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double quantite;
    private Double coutunitaire;
    private Double montanttimestre;
    private Double montantannuel;
    @OneToMany(mappedBy = "idmodelecosting", fetch = FetchType.LAZY)
    private Collection<Plafondbudget> plafondbudgetCollection;
    @JoinColumn(name = "idimputation", referencedColumnName = "idimputation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Imputation idimputation;
    @JoinColumn(name = "idunite", referencedColumnName = "idunite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unite idunite;

    public Modelecosting() {
    }

    public Modelecosting(Integer idmodelecosting) {
        this.idmodelecosting = idmodelecosting;
    }

    public Integer getIdmodelecosting() {
        return idmodelecosting;
    }

    public void setIdmodelecosting(Integer idmodelecosting) {
        this.idmodelecosting = idmodelecosting;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    public Double getMontanttimestre() {
        return montanttimestre;
    }

    public void setMontanttimestre(Double montanttimestre) {
        this.montanttimestre = montanttimestre;
    }

    public Double getMontantannuel() {
        return montantannuel;
    }

    public void setMontantannuel(Double montantannuel) {
        this.montantannuel = montantannuel;
    }

    @XmlTransient
    public Collection<Plafondbudget> getPlafondbudgetCollection() {
        return plafondbudgetCollection;
    }

    public void setPlafondbudgetCollection(Collection<Plafondbudget> plafondbudgetCollection) {
        this.plafondbudgetCollection = plafondbudgetCollection;
    }

    public Imputation getIdimputation() {
        return idimputation;
    }

    public void setIdimputation(Imputation idimputation) {
        this.idimputation = idimputation;
    }

    public Unite getIdunite() {
        return idunite;
    }

    public void setIdunite(Unite idunite) {
        this.idunite = idunite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmodelecosting != null ? idmodelecosting.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelecosting)) {
            return false;
        }
        Modelecosting other = (Modelecosting) object;
        if ((this.idmodelecosting == null && other.idmodelecosting != null) || (this.idmodelecosting != null && !this.idmodelecosting.equals(other.idmodelecosting))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Modelecosting[ idmodelecosting=" + idmodelecosting + " ]";
    }
    
}
