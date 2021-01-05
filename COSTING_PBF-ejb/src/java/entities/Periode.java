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
    @NamedQuery(name = "Periode.findAll", query = "SELECT p FROM Periode p"),
    @NamedQuery(name = "Periode.findByIdperiode", query = "SELECT p FROM Periode p WHERE p.idperiode = :idperiode"),
    @NamedQuery(name = "Periode.findByCode", query = "SELECT p FROM Periode p WHERE p.code = :code"),
    @NamedQuery(name = "Periode.findByNom", query = "SELECT p FROM Periode p WHERE p.nom = :nom")})
public class Periode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idperiode;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String nom;
    @OneToMany(mappedBy = "idperiode", fetch = FetchType.LAZY)
    private Collection<Contrat> contratCollection;
    @OneToMany(mappedBy = "idperiode", fetch = FetchType.LAZY)
    private Collection<Evaluationstructure> evaluationstructureCollection;
    @JoinColumn(name = "idfrequence", referencedColumnName = "idfrequence")
    @ManyToOne(fetch = FetchType.LAZY)
    private Frequence idfrequence;

    public Periode() {
    }

    public Periode(Integer idperiode) {
        this.idperiode = idperiode;
    }

    public Integer getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Integer idperiode) {
        this.idperiode = idperiode;
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

    public Frequence getIdfrequence() {
        return idfrequence;
    }

    public void setIdfrequence(Frequence idfrequence) {
        this.idfrequence = idfrequence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperiode != null ? idperiode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periode)) {
            return false;
        }
        Periode other = (Periode) object;
        if ((this.idperiode == null && other.idperiode != null) || (this.idperiode != null && !this.idperiode.equals(other.idperiode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Periode[ idperiode=" + idperiode + " ]";
    }
    
}
