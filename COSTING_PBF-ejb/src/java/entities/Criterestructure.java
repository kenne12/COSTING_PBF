/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Criterestructure.findAll", query = "SELECT c FROM Criterestructure c"),
    @NamedQuery(name = "Criterestructure.findByIdstructure", query = "SELECT c FROM Criterestructure c WHERE c.criterestructurePK.idstructure = :idstructure"),
    @NamedQuery(name = "Criterestructure.findByIdcritere", query = "SELECT c FROM Criterestructure c WHERE c.criterestructurePK.idcritere = :idcritere"),
    @NamedQuery(name = "Criterestructure.findByPoids", query = "SELECT c FROM Criterestructure c WHERE c.poids = :poids"),
    @NamedQuery(name = "Criterestructure.findByPointMax", query = "SELECT c FROM Criterestructure c WHERE c.pointMax = :pointMax")})
public class Criterestructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CriterestructurePK criterestructurePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double poids;
    @Column(name = "point_max")
    private Double pointMax;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Critere critere;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Structure structure;

    public Criterestructure() {
    }

    public Criterestructure(CriterestructurePK criterestructurePK) {
        this.criterestructurePK = criterestructurePK;
    }

    public Criterestructure(long idstructure, int idcritere) {
        this.criterestructurePK = new CriterestructurePK(idstructure, idcritere);
    }

    public CriterestructurePK getCriterestructurePK() {
        return criterestructurePK;
    }

    public void setCriterestructurePK(CriterestructurePK criterestructurePK) {
        this.criterestructurePK = criterestructurePK;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getPointMax() {
        return pointMax;
    }

    public void setPointMax(Double pointMax) {
        this.pointMax = pointMax;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (criterestructurePK != null ? criterestructurePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criterestructure)) {
            return false;
        }
        Criterestructure other = (Criterestructure) object;
        if ((this.criterestructurePK == null && other.criterestructurePK != null) || (this.criterestructurePK != null && !this.criterestructurePK.equals(other.criterestructurePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Criterestructure[ criterestructurePK=" + criterestructurePK + " ]";
    }
    
}
