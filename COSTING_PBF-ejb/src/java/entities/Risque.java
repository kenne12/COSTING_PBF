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
    @NamedQuery(name = "Risque.findAll", query = "SELECT r FROM Risque r"),
    @NamedQuery(name = "Risque.findByIdrisque", query = "SELECT r FROM Risque r WHERE r.idrisque = :idrisque"),
    @NamedQuery(name = "Risque.findByNom", query = "SELECT r FROM Risque r WHERE r.nom = :nom"),
    @NamedQuery(name = "Risque.findByEtat", query = "SELECT r FROM Risque r WHERE r.etat = :etat"),
    @NamedQuery(name = "Risque.findByDateEnregistre", query = "SELECT r FROM Risque r WHERE r.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Risque.findByDerniereModif", query = "SELECT r FROM Risque r WHERE r.derniereModif = :derniereModif")})
public class Risque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idrisque;
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
    @OneToMany(mappedBy = "idrisque", fetch = FetchType.LAZY)
    private Collection<Tache> tacheCollection;

    public Risque() {
    }

    public Risque(Long idrisque) {
        this.idrisque = idrisque;
    }

    public Long getIdrisque() {
        return idrisque;
    }

    public void setIdrisque(Long idrisque) {
        this.idrisque = idrisque;
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
    public Collection<Tache> getTacheCollection() {
        return tacheCollection;
    }

    public void setTacheCollection(Collection<Tache> tacheCollection) {
        this.tacheCollection = tacheCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrisque != null ? idrisque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Risque)) {
            return false;
        }
        Risque other = (Risque) object;
        if ((this.idrisque == null && other.idrisque != null) || (this.idrisque != null && !this.idrisque.equals(other.idrisque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Risque[ idrisque=" + idrisque + " ]";
    }
    
}
