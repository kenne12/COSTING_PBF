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
    @NamedQuery(name = "Axestrategique.findAll", query = "SELECT a FROM Axestrategique a"),
    @NamedQuery(name = "Axestrategique.findByIdaxestrategique", query = "SELECT a FROM Axestrategique a WHERE a.idaxestrategique = :idaxestrategique"),
    @NamedQuery(name = "Axestrategique.findByNom", query = "SELECT a FROM Axestrategique a WHERE a.nom = :nom"),
    @NamedQuery(name = "Axestrategique.findByEtat", query = "SELECT a FROM Axestrategique a WHERE a.etat = :etat"),
    @NamedQuery(name = "Axestrategique.findByDateEnregistre", query = "SELECT a FROM Axestrategique a WHERE a.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Axestrategique.findByDerniereModif", query = "SELECT a FROM Axestrategique a WHERE a.derniereModif = :derniereModif")})
public class Axestrategique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idaxestrategique;
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
    @OneToMany(mappedBy = "idaxestrategique", fetch = FetchType.LAZY)
    private Collection<Programme> programmeCollection;

    public Axestrategique() {
    }

    public Axestrategique(Long idaxestrategique) {
        this.idaxestrategique = idaxestrategique;
    }

    public Long getIdaxestrategique() {
        return idaxestrategique;
    }

    public void setIdaxestrategique(Long idaxestrategique) {
        this.idaxestrategique = idaxestrategique;
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
    public Collection<Programme> getProgrammeCollection() {
        return programmeCollection;
    }

    public void setProgrammeCollection(Collection<Programme> programmeCollection) {
        this.programmeCollection = programmeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaxestrategique != null ? idaxestrategique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Axestrategique)) {
            return false;
        }
        Axestrategique other = (Axestrategique) object;
        if ((this.idaxestrategique == null && other.idaxestrategique != null) || (this.idaxestrategique != null && !this.idaxestrategique.equals(other.idaxestrategique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Axestrategique[ idaxestrategique=" + idaxestrategique + " ]";
    }
    
}
