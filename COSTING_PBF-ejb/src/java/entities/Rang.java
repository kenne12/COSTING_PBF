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
    @NamedQuery(name = "Rang.findAll", query = "SELECT r FROM Rang r"),
    @NamedQuery(name = "Rang.findByIdrang", query = "SELECT r FROM Rang r WHERE r.idrang = :idrang"),
    @NamedQuery(name = "Rang.findByNom", query = "SELECT r FROM Rang r WHERE r.nom = :nom"),
    @NamedQuery(name = "Rang.findByNumero", query = "SELECT r FROM Rang r WHERE r.numero = :numero"),
    @NamedQuery(name = "Rang.findByEtat", query = "SELECT r FROM Rang r WHERE r.etat = :etat"),
    @NamedQuery(name = "Rang.findByDateEnregistre", query = "SELECT r FROM Rang r WHERE r.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Rang.findByDerniereModif", query = "SELECT r FROM Rang r WHERE r.derniereModif = :derniereModif")})
public class Rang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idrang;
    @Size(max = 254)
    private String nom;
    private Integer numero;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @OneToMany(mappedBy = "idrang", fetch = FetchType.LAZY)
    private Collection<Activite> activiteCollection;

    public Rang() {
    }

    public Rang(Long idrang) {
        this.idrang = idrang;
    }

    public Long getIdrang() {
        return idrang;
    }

    public void setIdrang(Long idrang) {
        this.idrang = idrang;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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
    public Collection<Activite> getActiviteCollection() {
        return activiteCollection;
    }

    public void setActiviteCollection(Collection<Activite> activiteCollection) {
        this.activiteCollection = activiteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrang != null ? idrang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rang)) {
            return false;
        }
        Rang other = (Rang) object;
        if ((this.idrang == null && other.idrang != null) || (this.idrang != null && !this.idrang.equals(other.idrang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rang[ idrang=" + idrang + " ]";
    }
    
}
