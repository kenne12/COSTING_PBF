/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Plafondbudget.findAll", query = "SELECT p FROM Plafondbudget p"),
    @NamedQuery(name = "Plafondbudget.findByIdplafondbudget", query = "SELECT p FROM Plafondbudget p WHERE p.idplafondbudget = :idplafondbudget"),
    @NamedQuery(name = "Plafondbudget.findByQuantite", query = "SELECT p FROM Plafondbudget p WHERE p.quantite = :quantite"),
    @NamedQuery(name = "Plafondbudget.findByCu", query = "SELECT p FROM Plafondbudget p WHERE p.cu = :cu"),
    @NamedQuery(name = "Plafondbudget.findByMontantannuel", query = "SELECT p FROM Plafondbudget p WHERE p.montantannuel = :montantannuel"),
    @NamedQuery(name = "Plafondbudget.findByMontanttrim", query = "SELECT p FROM Plafondbudget p WHERE p.montanttrim = :montanttrim"),
    @NamedQuery(name = "Plafondbudget.findByCoefficient", query = "SELECT p FROM Plafondbudget p WHERE p.coefficient = :coefficient")})
public class Plafondbudget implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idplafondbudget;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double quantite;
    private Double cu;
    private Double montantannuel;
    private Double montanttrim;
    private Integer coefficient;
    @JoinColumn(name = "idbudget", referencedColumnName = "idbudget")
    @ManyToOne(fetch = FetchType.LAZY)
    private Budget idbudget;
    @JoinColumn(name = "idmodelecosting", referencedColumnName = "idmodelecosting")
    @ManyToOne(fetch = FetchType.LAZY)
    private Modelecosting idmodelecosting;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plafondbudget", fetch = FetchType.LAZY)
    private Collection<CostingPbQte> costingPbQteCollection;

    public Plafondbudget() {
    }

    public Plafondbudget(Long idplafondbudget) {
        this.idplafondbudget = idplafondbudget;
    }

    public Long getIdplafondbudget() {
        return idplafondbudget;
    }

    public void setIdplafondbudget(Long idplafondbudget) {
        this.idplafondbudget = idplafondbudget;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getCu() {
        return cu;
    }

    public void setCu(Double cu) {
        this.cu = cu;
    }

    public Double getMontantannuel() {
        return montantannuel;
    }

    public void setMontantannuel(Double montantannuel) {
        this.montantannuel = montantannuel;
    }

    public Double getMontanttrim() {
        return montanttrim;
    }

    public void setMontanttrim(Double montanttrim) {
        this.montanttrim = montanttrim;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public Budget getIdbudget() {
        return idbudget;
    }

    public void setIdbudget(Budget idbudget) {
        this.idbudget = idbudget;
    }

    public Modelecosting getIdmodelecosting() {
        return idmodelecosting;
    }

    public void setIdmodelecosting(Modelecosting idmodelecosting) {
        this.idmodelecosting = idmodelecosting;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @XmlTransient
    public Collection<CostingPbQte> getCostingPbQteCollection() {
        return costingPbQteCollection;
    }

    public void setCostingPbQteCollection(Collection<CostingPbQte> costingPbQteCollection) {
        this.costingPbQteCollection = costingPbQteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplafondbudget != null ? idplafondbudget.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plafondbudget)) {
            return false;
        }
        Plafondbudget other = (Plafondbudget) object;
        if ((this.idplafondbudget == null && other.idplafondbudget != null) || (this.idplafondbudget != null && !this.idplafondbudget.equals(other.idplafondbudget))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Plafondbudget[ idplafondbudget=" + idplafondbudget + " ]";
    }
    
}
