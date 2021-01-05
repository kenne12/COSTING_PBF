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
    @NamedQuery(name = "Naturetache.findAll", query = "SELECT n FROM Naturetache n"),
    @NamedQuery(name = "Naturetache.findByIdnaturetache", query = "SELECT n FROM Naturetache n WHERE n.idnaturetache = :idnaturetache"),
    @NamedQuery(name = "Naturetache.findByNom", query = "SELECT n FROM Naturetache n WHERE n.nom = :nom")})
public class Naturetache implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idnaturetache;
    @Size(max = 254)
    private String nom;
    @OneToMany(mappedBy = "idnaturetache", fetch = FetchType.LAZY)
    private Collection<Tache> tacheCollection;
    @OneToMany(mappedBy = "idnaturetache", fetch = FetchType.LAZY)
    private Collection<NatureT> natureTCollection;
    @OneToMany(mappedBy = "idnaturetache", fetch = FetchType.LAZY)
    private Collection<Typefinancement> typefinancementCollection;

    public Naturetache() {
    }

    public Naturetache(Integer idnaturetache) {
        this.idnaturetache = idnaturetache;
    }

    public Integer getIdnaturetache() {
        return idnaturetache;
    }

    public void setIdnaturetache(Integer idnaturetache) {
        this.idnaturetache = idnaturetache;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<Tache> getTacheCollection() {
        return tacheCollection;
    }

    public void setTacheCollection(Collection<Tache> tacheCollection) {
        this.tacheCollection = tacheCollection;
    }

    @XmlTransient
    public Collection<NatureT> getNatureTCollection() {
        return natureTCollection;
    }

    public void setNatureTCollection(Collection<NatureT> natureTCollection) {
        this.natureTCollection = natureTCollection;
    }

    @XmlTransient
    public Collection<Typefinancement> getTypefinancementCollection() {
        return typefinancementCollection;
    }

    public void setTypefinancementCollection(Collection<Typefinancement> typefinancementCollection) {
        this.typefinancementCollection = typefinancementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnaturetache != null ? idnaturetache.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Naturetache)) {
            return false;
        }
        Naturetache other = (Naturetache) object;
        if ((this.idnaturetache == null && other.idnaturetache != null) || (this.idnaturetache != null && !this.idnaturetache.equals(other.idnaturetache))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Naturetache[ idnaturetache=" + idnaturetache + " ]";
    }
    
}
