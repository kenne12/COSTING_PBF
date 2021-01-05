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
    @NamedQuery(name = "Rubrique.findAll", query = "SELECT r FROM Rubrique r"),
    @NamedQuery(name = "Rubrique.findByIdrubrique", query = "SELECT r FROM Rubrique r WHERE r.idrubrique = :idrubrique"),
    @NamedQuery(name = "Rubrique.findByCode", query = "SELECT r FROM Rubrique r WHERE r.code = :code"),
    @NamedQuery(name = "Rubrique.findByNom", query = "SELECT r FROM Rubrique r WHERE r.nom = :nom")})
public class Rubrique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubrique;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String nom;
    @OneToMany(mappedBy = "idrubrique", fetch = FetchType.LAZY)
    private Collection<Sousrubrique> sousrubriqueCollection;

    public Rubrique() {
    }

    public Rubrique(Integer idrubrique) {
        this.idrubrique = idrubrique;
    }

    public Integer getIdrubrique() {
        return idrubrique;
    }

    public void setIdrubrique(Integer idrubrique) {
        this.idrubrique = idrubrique;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<Sousrubrique> getSousrubriqueCollection() {
        return sousrubriqueCollection;
    }

    public void setSousrubriqueCollection(Collection<Sousrubrique> sousrubriqueCollection) {
        this.sousrubriqueCollection = sousrubriqueCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubrique != null ? idrubrique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubrique)) {
            return false;
        }
        Rubrique other = (Rubrique) object;
        if ((this.idrubrique == null && other.idrubrique != null) || (this.idrubrique != null && !this.idrubrique.equals(other.idrubrique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubrique[ idrubrique=" + idrubrique + " ]";
    }
    
}
