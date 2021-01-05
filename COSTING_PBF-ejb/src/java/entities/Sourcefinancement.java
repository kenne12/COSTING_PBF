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
    @NamedQuery(name = "Sourcefinancement.findAll", query = "SELECT s FROM Sourcefinancement s"),
    @NamedQuery(name = "Sourcefinancement.findByIdsourcefinancement", query = "SELECT s FROM Sourcefinancement s WHERE s.idsourcefinancement = :idsourcefinancement"),
    @NamedQuery(name = "Sourcefinancement.findByNom", query = "SELECT s FROM Sourcefinancement s WHERE s.nom = :nom"),
    @NamedQuery(name = "Sourcefinancement.findByEtat", query = "SELECT s FROM Sourcefinancement s WHERE s.etat = :etat"),
    @NamedQuery(name = "Sourcefinancement.findByDateEnregistre", query = "SELECT s FROM Sourcefinancement s WHERE s.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Sourcefinancement.findByDerniereModif", query = "SELECT s FROM Sourcefinancement s WHERE s.derniereModif = :derniereModif")})
public class Sourcefinancement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idsourcefinancement;
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
    @OneToMany(mappedBy = "idsourcefinancement", fetch = FetchType.LAZY)
    private Collection<Bailleurfond> bailleurfondCollection;

    public Sourcefinancement() {
    }

    public Sourcefinancement(Long idsourcefinancement) {
        this.idsourcefinancement = idsourcefinancement;
    }

    public Long getIdsourcefinancement() {
        return idsourcefinancement;
    }

    public void setIdsourcefinancement(Long idsourcefinancement) {
        this.idsourcefinancement = idsourcefinancement;
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
    public Collection<Bailleurfond> getBailleurfondCollection() {
        return bailleurfondCollection;
    }

    public void setBailleurfondCollection(Collection<Bailleurfond> bailleurfondCollection) {
        this.bailleurfondCollection = bailleurfondCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsourcefinancement != null ? idsourcefinancement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sourcefinancement)) {
            return false;
        }
        Sourcefinancement other = (Sourcefinancement) object;
        if ((this.idsourcefinancement == null && other.idsourcefinancement != null) || (this.idsourcefinancement != null && !this.idsourcefinancement.equals(other.idsourcefinancement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sourcefinancement[ idsourcefinancement=" + idsourcefinancement + " ]";
    }
    
}
