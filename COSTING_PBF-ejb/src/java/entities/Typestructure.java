/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typestructure.findAll", query = "SELECT t FROM Typestructure t"),
    @NamedQuery(name = "Typestructure.findByIdtypestructure", query = "SELECT t FROM Typestructure t WHERE t.idtypestructure = :idtypestructure"),
    @NamedQuery(name = "Typestructure.findByCoefficient", query = "SELECT t FROM Typestructure t WHERE t.coefficient = :coefficient"),
    @NamedQuery(name = "Typestructure.findByTauxMaxVariation", query = "SELECT t FROM Typestructure t WHERE t.tauxMaxVariation = :tauxMaxVariation"),
    @NamedQuery(name = "Typestructure.findByTauxMaxBq", query = "SELECT t FROM Typestructure t WHERE t.tauxMaxBq = :tauxMaxBq"),
    @NamedQuery(name = "Typestructure.findByNom", query = "SELECT t FROM Typestructure t WHERE t.nom = :nom"),
    @NamedQuery(name = "Typestructure.findByEtat", query = "SELECT t FROM Typestructure t WHERE t.etat = :etat"),
    @NamedQuery(name = "Typestructure.findByDateEnregistre", query = "SELECT t FROM Typestructure t WHERE t.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Typestructure.findByDerniereModif", query = "SELECT t FROM Typestructure t WHERE t.derniereModif = :derniereModif")})
public class Typestructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idtypestructure;
    private Integer coefficient;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taux_max_variation")
    private Double tauxMaxVariation;
    @Column(name = "taux_max_bq")
    private Double tauxMaxBq;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private Collection<Structure> structureCollection;

    public Typestructure() {
    }

    public Typestructure(Long idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    public Long getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Long idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public Double getTauxMaxVariation() {
        return tauxMaxVariation;
    }

    public void setTauxMaxVariation(Double tauxMaxVariation) {
        this.tauxMaxVariation = tauxMaxVariation;
    }

    public Double getTauxMaxBq() {
        return tauxMaxBq;
    }

    public void setTauxMaxBq(Double tauxMaxBq) {
        this.tauxMaxBq = tauxMaxBq;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    @XmlTransient
    public Collection<Structure> getStructureCollection() {
        return structureCollection;
    }

    public void setStructureCollection(Collection<Structure> structureCollection) {
        this.structureCollection = structureCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypestructure != null ? idtypestructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typestructure)) {
            return false;
        }
        Typestructure other = (Typestructure) object;
        if ((this.idtypestructure == null && other.idtypestructure != null) || (this.idtypestructure != null && !this.idtypestructure.equals(other.idtypestructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typestructure[ idtypestructure=" + idtypestructure + " ]";
    }
    
}
