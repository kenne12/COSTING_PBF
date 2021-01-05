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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Sousrubrique.findAll", query = "SELECT s FROM Sousrubrique s"),
    @NamedQuery(name = "Sousrubrique.findByIdsousrubrique", query = "SELECT s FROM Sousrubrique s WHERE s.idsousrubrique = :idsousrubrique"),
    @NamedQuery(name = "Sousrubrique.findByNom", query = "SELECT s FROM Sousrubrique s WHERE s.nom = :nom"),
    @NamedQuery(name = "Sousrubrique.findByCode", query = "SELECT s FROM Sousrubrique s WHERE s.code = :code")})
public class Sousrubrique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsousrubrique;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    @OneToMany(mappedBy = "idsousrubrique", fetch = FetchType.LAZY)
    private Collection<Imputation> imputationCollection;
    @JoinColumn(name = "idrubrique", referencedColumnName = "idrubrique")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubrique idrubrique;

    public Sousrubrique() {
    }

    public Sousrubrique(Integer idsousrubrique) {
        this.idsousrubrique = idsousrubrique;
    }

    public Integer getIdsousrubrique() {
        return idsousrubrique;
    }

    public void setIdsousrubrique(Integer idsousrubrique) {
        this.idsousrubrique = idsousrubrique;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Collection<Imputation> getImputationCollection() {
        return imputationCollection;
    }

    public void setImputationCollection(Collection<Imputation> imputationCollection) {
        this.imputationCollection = imputationCollection;
    }

    public Rubrique getIdrubrique() {
        return idrubrique;
    }

    public void setIdrubrique(Rubrique idrubrique) {
        this.idrubrique = idrubrique;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsousrubrique != null ? idsousrubrique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sousrubrique)) {
            return false;
        }
        Sousrubrique other = (Sousrubrique) object;
        if ((this.idsousrubrique == null && other.idsousrubrique != null) || (this.idsousrubrique != null && !this.idsousrubrique.equals(other.idsousrubrique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sousrubrique[ idsousrubrique=" + idsousrubrique + " ]";
    }
    
}
