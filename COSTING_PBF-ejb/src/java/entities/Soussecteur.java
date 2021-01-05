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
    @NamedQuery(name = "Soussecteur.findAll", query = "SELECT s FROM Soussecteur s"),
    @NamedQuery(name = "Soussecteur.findByIdsoussecteur", query = "SELECT s FROM Soussecteur s WHERE s.idsoussecteur = :idsoussecteur"),
    @NamedQuery(name = "Soussecteur.findByNom", query = "SELECT s FROM Soussecteur s WHERE s.nom = :nom"),
    @NamedQuery(name = "Soussecteur.findByEtat", query = "SELECT s FROM Soussecteur s WHERE s.etat = :etat"),
    @NamedQuery(name = "Soussecteur.findByDateEnregistre", query = "SELECT s FROM Soussecteur s WHERE s.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Soussecteur.findByDerniereModif", query = "SELECT s FROM Soussecteur s WHERE s.derniereModif = :derniereModif")})
public class Soussecteur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idsoussecteur;
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
    @OneToMany(mappedBy = "idsoussecteur", fetch = FetchType.LAZY)
    private Collection<Institution> institutionCollection;
    @JoinColumn(name = "idsecteur", referencedColumnName = "idsecteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Secteur idsecteur;
    @OneToMany(mappedBy = "idsoussecteur", fetch = FetchType.LAZY)
    private Collection<Programme> programmeCollection;

    public Soussecteur() {
    }

    public Soussecteur(Long idsoussecteur) {
        this.idsoussecteur = idsoussecteur;
    }

    public Long getIdsoussecteur() {
        return idsoussecteur;
    }

    public void setIdsoussecteur(Long idsoussecteur) {
        this.idsoussecteur = idsoussecteur;
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
    public Collection<Institution> getInstitutionCollection() {
        return institutionCollection;
    }

    public void setInstitutionCollection(Collection<Institution> institutionCollection) {
        this.institutionCollection = institutionCollection;
    }

    public Secteur getIdsecteur() {
        return idsecteur;
    }

    public void setIdsecteur(Secteur idsecteur) {
        this.idsecteur = idsecteur;
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
        hash += (idsoussecteur != null ? idsoussecteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soussecteur)) {
            return false;
        }
        Soussecteur other = (Soussecteur) object;
        if ((this.idsoussecteur == null && other.idsoussecteur != null) || (this.idsoussecteur != null && !this.idsoussecteur.equals(other.idsoussecteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Soussecteur[ idsoussecteur=" + idsoussecteur + " ]";
    }
    
}
