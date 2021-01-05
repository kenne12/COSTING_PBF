/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Frequence.findAll", query = "SELECT f FROM Frequence f"),
    @NamedQuery(name = "Frequence.findByIdfrequence", query = "SELECT f FROM Frequence f WHERE f.idfrequence = :idfrequence"),
    @NamedQuery(name = "Frequence.findByNom", query = "SELECT f FROM Frequence f WHERE f.nom = :nom")})
public class Frequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idfrequence;
    @Size(max = 254)
    private String nom;
    @OneToMany(mappedBy = "idfrequence", fetch = FetchType.LAZY)
    private Collection<Contrat> contratCollection;
    @OneToMany(mappedBy = "idfrequence", fetch = FetchType.LAZY)
    private Collection<Periode> periodeCollection;

    public Frequence() {
    }

    public Frequence(Integer idfrequence) {
        this.idfrequence = idfrequence;
    }

    public Integer getIdfrequence() {
        return idfrequence;
    }

    public void setIdfrequence(Integer idfrequence) {
        this.idfrequence = idfrequence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<Contrat> getContratCollection() {
        return contratCollection;
    }

    public void setContratCollection(Collection<Contrat> contratCollection) {
        this.contratCollection = contratCollection;
    }

    @XmlTransient
    public Collection<Periode> getPeriodeCollection() {
        return periodeCollection;
    }

    public void setPeriodeCollection(Collection<Periode> periodeCollection) {
        this.periodeCollection = periodeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfrequence != null ? idfrequence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frequence)) {
            return false;
        }
        Frequence other = (Frequence) object;
        if ((this.idfrequence == null && other.idfrequence != null) || (this.idfrequence != null && !this.idfrequence.equals(other.idfrequence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Frequence[ idfrequence=" + idfrequence + " ]";
    }
    
}
