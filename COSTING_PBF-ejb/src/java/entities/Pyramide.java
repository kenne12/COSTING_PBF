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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Pyramide.findAll", query = "SELECT p FROM Pyramide p"),
    @NamedQuery(name = "Pyramide.findByIdpyramide", query = "SELECT p FROM Pyramide p WHERE p.idpyramide = :idpyramide"),
    @NamedQuery(name = "Pyramide.findByNom", query = "SELECT p FROM Pyramide p WHERE p.nom = :nom"),
    @NamedQuery(name = "Pyramide.findBySuperficie", query = "SELECT p FROM Pyramide p WHERE p.superficie = :superficie"),
    @NamedQuery(name = "Pyramide.findByPopulation", query = "SELECT p FROM Pyramide p WHERE p.population = :population"),
    @NamedQuery(name = "Pyramide.findByEtat", query = "SELECT p FROM Pyramide p WHERE p.etat = :etat"),
    @NamedQuery(name = "Pyramide.findByDateEnregistre", query = "SELECT p FROM Pyramide p WHERE p.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Pyramide.findByDerniereModif", query = "SELECT p FROM Pyramide p WHERE p.derniereModif = :derniereModif")})
public class Pyramide implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idpyramide;
    @Size(max = 254)
    private String nom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double superficie;
    private Double population;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @OneToMany(mappedBy = "idpyramide", fetch = FetchType.LAZY)
    private Collection<Structure> structureCollection;
    @JoinColumn(name = "idniveaupyramide", referencedColumnName = "idniveaupyramide")
    @ManyToOne(fetch = FetchType.LAZY)
    private Niveaupyramide idniveaupyramide;

    public Pyramide() {
    }

    public Pyramide(Long idpyramide) {
        this.idpyramide = idpyramide;
    }

    public Long getIdpyramide() {
        return idpyramide;
    }

    public void setIdpyramide(Long idpyramide) {
        this.idpyramide = idpyramide;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
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

    public Niveaupyramide getIdniveaupyramide() {
        return idniveaupyramide;
    }

    public void setIdniveaupyramide(Niveaupyramide idniveaupyramide) {
        this.idniveaupyramide = idniveaupyramide;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpyramide != null ? idpyramide.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pyramide)) {
            return false;
        }
        Pyramide other = (Pyramide) object;
        if ((this.idpyramide == null && other.idpyramide != null) || (this.idpyramide != null && !this.idpyramide.equals(other.idpyramide))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pyramide[ idpyramide=" + idpyramide + " ]";
    }
    
}
