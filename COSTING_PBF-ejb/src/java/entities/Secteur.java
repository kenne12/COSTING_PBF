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
    @NamedQuery(name = "Secteur.findAll", query = "SELECT s FROM Secteur s"),
    @NamedQuery(name = "Secteur.findByIdsecteur", query = "SELECT s FROM Secteur s WHERE s.idsecteur = :idsecteur"),
    @NamedQuery(name = "Secteur.findByNom", query = "SELECT s FROM Secteur s WHERE s.nom = :nom"),
    @NamedQuery(name = "Secteur.findByEtat", query = "SELECT s FROM Secteur s WHERE s.etat = :etat"),
    @NamedQuery(name = "Secteur.findByDateEnregistre", query = "SELECT s FROM Secteur s WHERE s.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Secteur.findByDateModif", query = "SELECT s FROM Secteur s WHERE s.dateModif = :dateModif")})
public class Secteur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idsecteur;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "date_modif")
    @Temporal(TemporalType.DATE)
    private Date dateModif;
    @OneToMany(mappedBy = "idsecteur", fetch = FetchType.LAZY)
    private Collection<Soussecteur> soussecteurCollection;

    public Secteur() {
    }

    public Secteur(Long idsecteur) {
        this.idsecteur = idsecteur;
    }

    public Long getIdsecteur() {
        return idsecteur;
    }

    public void setIdsecteur(Long idsecteur) {
        this.idsecteur = idsecteur;
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

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    @XmlTransient
    public Collection<Soussecteur> getSoussecteurCollection() {
        return soussecteurCollection;
    }

    public void setSoussecteurCollection(Collection<Soussecteur> soussecteurCollection) {
        this.soussecteurCollection = soussecteurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsecteur != null ? idsecteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Secteur)) {
            return false;
        }
        Secteur other = (Secteur) object;
        if ((this.idsecteur == null && other.idsecteur != null) || (this.idsecteur != null && !this.idsecteur.equals(other.idsecteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Secteur[ idsecteur=" + idsecteur + " ]";
    }
    
}
