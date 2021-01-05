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
    @NamedQuery(name = "Typeactivite.findAll", query = "SELECT t FROM Typeactivite t"),
    @NamedQuery(name = "Typeactivite.findByIdtypeactivite", query = "SELECT t FROM Typeactivite t WHERE t.idtypeactivite = :idtypeactivite"),
    @NamedQuery(name = "Typeactivite.findByNom", query = "SELECT t FROM Typeactivite t WHERE t.nom = :nom"),
    @NamedQuery(name = "Typeactivite.findByNumero", query = "SELECT t FROM Typeactivite t WHERE t.numero = :numero"),
    @NamedQuery(name = "Typeactivite.findByEtat", query = "SELECT t FROM Typeactivite t WHERE t.etat = :etat"),
    @NamedQuery(name = "Typeactivite.findByDateEnregistre", query = "SELECT t FROM Typeactivite t WHERE t.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Typeactivite.findByDerniereModif", query = "SELECT t FROM Typeactivite t WHERE t.derniereModif = :derniereModif")})
public class Typeactivite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idtypeactivite;
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
    @OneToMany(mappedBy = "idtypeactivite", fetch = FetchType.LAZY)
    private Collection<Activite> activiteCollection;

    public Typeactivite() {
    }

    public Typeactivite(Long idtypeactivite) {
        this.idtypeactivite = idtypeactivite;
    }

    public Long getIdtypeactivite() {
        return idtypeactivite;
    }

    public void setIdtypeactivite(Long idtypeactivite) {
        this.idtypeactivite = idtypeactivite;
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
        hash += (idtypeactivite != null ? idtypeactivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeactivite)) {
            return false;
        }
        Typeactivite other = (Typeactivite) object;
        if ((this.idtypeactivite == null && other.idtypeactivite != null) || (this.idtypeactivite != null && !this.idtypeactivite.equals(other.idtypeactivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typeactivite[ idtypeactivite=" + idtypeactivite + " ]";
    }
    
}
