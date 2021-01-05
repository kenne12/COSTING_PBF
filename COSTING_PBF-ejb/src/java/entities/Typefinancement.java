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
    @NamedQuery(name = "Typefinancement.findAll", query = "SELECT t FROM Typefinancement t"),
    @NamedQuery(name = "Typefinancement.findByIdtypefinancement", query = "SELECT t FROM Typefinancement t WHERE t.idtypefinancement = :idtypefinancement"),
    @NamedQuery(name = "Typefinancement.findByNom", query = "SELECT t FROM Typefinancement t WHERE t.nom = :nom"),
    @NamedQuery(name = "Typefinancement.findByEtat", query = "SELECT t FROM Typefinancement t WHERE t.etat = :etat"),
    @NamedQuery(name = "Typefinancement.findByDateEnregistre", query = "SELECT t FROM Typefinancement t WHERE t.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Typefinancement.findByDerniereModif", query = "SELECT t FROM Typefinancement t WHERE t.derniereModif = :derniereModif")})
public class Typefinancement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtypefinancement;
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
    @OneToMany(mappedBy = "idtypefinancement", fetch = FetchType.LAZY)
    private Collection<Tache> tacheCollection;
    @JoinColumn(name = "idnaturetache", referencedColumnName = "idnaturetache")
    @ManyToOne(fetch = FetchType.LAZY)
    private Naturetache idnaturetache;

    public Typefinancement() {
    }

    public Typefinancement(Integer idtypefinancement) {
        this.idtypefinancement = idtypefinancement;
    }

    public Integer getIdtypefinancement() {
        return idtypefinancement;
    }

    public void setIdtypefinancement(Integer idtypefinancement) {
        this.idtypefinancement = idtypefinancement;
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

    public Naturetache getIdnaturetache() {
        return idnaturetache;
    }

    public void setIdnaturetache(Naturetache idnaturetache) {
        this.idnaturetache = idnaturetache;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypefinancement != null ? idtypefinancement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typefinancement)) {
            return false;
        }
        Typefinancement other = (Typefinancement) object;
        if ((this.idtypefinancement == null && other.idtypefinancement != null) || (this.idtypefinancement != null && !this.idtypefinancement.equals(other.idtypefinancement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typefinancement[ idtypefinancement=" + idtypefinancement + " ]";
    }
    
}
