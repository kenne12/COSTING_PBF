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
    @NamedQuery(name = "Budget.findAll", query = "SELECT b FROM Budget b"),
    @NamedQuery(name = "Budget.findByIdbudget", query = "SELECT b FROM Budget b WHERE b.idbudget = :idbudget"),
    @NamedQuery(name = "Budget.findByNom", query = "SELECT b FROM Budget b WHERE b.nom = :nom"),
    @NamedQuery(name = "Budget.findByAnnee", query = "SELECT b FROM Budget b WHERE b.annee = :annee")})
public class Budget implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idbudget;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String annee;
    @OneToMany(mappedBy = "idbudget", fetch = FetchType.LAZY)
    private Collection<Tache> tacheCollection;
    @OneToMany(mappedBy = "idbudget", fetch = FetchType.LAZY)
    private Collection<Contrat> contratCollection;
    @OneToMany(mappedBy = "idbudget", fetch = FetchType.LAZY)
    private Collection<Evaluationstructure> evaluationstructureCollection;
    @OneToMany(mappedBy = "idbudget", fetch = FetchType.LAZY)
    private Collection<Plafondbudget> plafondbudgetCollection;
    @OneToMany(mappedBy = "idbudget", fetch = FetchType.LAZY)
    private Collection<Moyens> moyensCollection;

    public Budget() {
    }

    public Budget(Integer idbudget) {
        this.idbudget = idbudget;
    }

    public Integer getIdbudget() {
        return idbudget;
    }

    public void setIdbudget(Integer idbudget) {
        this.idbudget = idbudget;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    @XmlTransient
    public Collection<Tache> getTacheCollection() {
        return tacheCollection;
    }

    public void setTacheCollection(Collection<Tache> tacheCollection) {
        this.tacheCollection = tacheCollection;
    }

    @XmlTransient
    public Collection<Contrat> getContratCollection() {
        return contratCollection;
    }

    public void setContratCollection(Collection<Contrat> contratCollection) {
        this.contratCollection = contratCollection;
    }

    @XmlTransient
    public Collection<Evaluationstructure> getEvaluationstructureCollection() {
        return evaluationstructureCollection;
    }

    public void setEvaluationstructureCollection(Collection<Evaluationstructure> evaluationstructureCollection) {
        this.evaluationstructureCollection = evaluationstructureCollection;
    }

    @XmlTransient
    public Collection<Plafondbudget> getPlafondbudgetCollection() {
        return plafondbudgetCollection;
    }

    public void setPlafondbudgetCollection(Collection<Plafondbudget> plafondbudgetCollection) {
        this.plafondbudgetCollection = plafondbudgetCollection;
    }

    @XmlTransient
    public Collection<Moyens> getMoyensCollection() {
        return moyensCollection;
    }

    public void setMoyensCollection(Collection<Moyens> moyensCollection) {
        this.moyensCollection = moyensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbudget != null ? idbudget.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Budget)) {
            return false;
        }
        Budget other = (Budget) object;
        if ((this.idbudget == null && other.idbudget != null) || (this.idbudget != null && !this.idbudget.equals(other.idbudget))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Budget[ idbudget=" + idbudget + " ]";
    }
    
}
