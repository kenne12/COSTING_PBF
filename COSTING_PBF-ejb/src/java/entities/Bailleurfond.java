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
    @NamedQuery(name = "Bailleurfond.findAll", query = "SELECT b FROM Bailleurfond b"),
    @NamedQuery(name = "Bailleurfond.findByIdbailleurfond", query = "SELECT b FROM Bailleurfond b WHERE b.idbailleurfond = :idbailleurfond"),
    @NamedQuery(name = "Bailleurfond.findByNom", query = "SELECT b FROM Bailleurfond b WHERE b.nom = :nom"),
    @NamedQuery(name = "Bailleurfond.findByEtat", query = "SELECT b FROM Bailleurfond b WHERE b.etat = :etat"),
    @NamedQuery(name = "Bailleurfond.findByDateEnregistre", query = "SELECT b FROM Bailleurfond b WHERE b.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Bailleurfond.findByDerniereModif", query = "SELECT b FROM Bailleurfond b WHERE b.derniereModif = :derniereModif")})
public class Bailleurfond implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idbailleurfond;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String etat;
    @Size(max = 254)
    private String code;
    private double montantdisponible;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @OneToMany(mappedBy = "idbailleurfond", fetch = FetchType.LAZY)
    private Collection<Tache> tacheCollection;
    @JoinColumn(name = "idsourcefinancement", referencedColumnName = "idsourcefinancement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sourcefinancement idsourcefinancement;

    public Bailleurfond() {
    }

    public Bailleurfond(Long idbailleurfond) {
        this.idbailleurfond = idbailleurfond;
    }

    public Long getIdbailleurfond() {
        return idbailleurfond;
    }

    public void setIdbailleurfond(Long idbailleurfond) {
        this.idbailleurfond = idbailleurfond;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMontantdisponible() {
        return montantdisponible;
    }

    public void setMontantdisponible(double montantdisponible) {
        this.montantdisponible = montantdisponible;
    }

    @XmlTransient
    public Collection<Tache> getTacheCollection() {
        return tacheCollection;
    }

    public void setTacheCollection(Collection<Tache> tacheCollection) {
        this.tacheCollection = tacheCollection;
    }

    public Sourcefinancement getIdsourcefinancement() {
        return idsourcefinancement;
    }

    public void setIdsourcefinancement(Sourcefinancement idsourcefinancement) {
        this.idsourcefinancement = idsourcefinancement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbailleurfond != null ? idbailleurfond.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bailleurfond)) {
            return false;
        }
        Bailleurfond other = (Bailleurfond) object;
        if ((this.idbailleurfond == null && other.idbailleurfond != null) || (this.idbailleurfond != null && !this.idbailleurfond.equals(other.idbailleurfond))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Bailleurfond[ idbailleurfond=" + idbailleurfond + " ]";
    }

}
