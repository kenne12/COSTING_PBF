/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "costing_pb_qte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CostingPbQte.findAll", query = "SELECT c FROM CostingPbQte c"),
    @NamedQuery(name = "CostingPbQte.findByIdplafondbudget", query = "SELECT c FROM CostingPbQte c WHERE c.costingPbQtePK.idplafondbudget = :idplafondbudget"),
    @NamedQuery(name = "CostingPbQte.findByIduniteCosting", query = "SELECT c FROM CostingPbQte c WHERE c.costingPbQtePK.iduniteCosting = :iduniteCosting"),
    @NamedQuery(name = "CostingPbQte.findByQuantite", query = "SELECT c FROM CostingPbQte c WHERE c.quantite = :quantite")})
public class CostingPbQte implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CostingPbQtePK costingPbQtePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double quantite;
    @JoinColumn(name = "idplafondbudget", referencedColumnName = "idplafondbudget", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Plafondbudget plafondbudget;
    @JoinColumn(name = "idunite_costing", referencedColumnName = "idunite_costing", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UniteCosting uniteCosting;

    public CostingPbQte() {
    }

    public CostingPbQte(CostingPbQtePK costingPbQtePK) {
        this.costingPbQtePK = costingPbQtePK;
    }

    public CostingPbQte(long idplafondbudget, int iduniteCosting) {
        this.costingPbQtePK = new CostingPbQtePK(idplafondbudget, iduniteCosting);
    }

    public CostingPbQtePK getCostingPbQtePK() {
        return costingPbQtePK;
    }

    public void setCostingPbQtePK(CostingPbQtePK costingPbQtePK) {
        this.costingPbQtePK = costingPbQtePK;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Plafondbudget getPlafondbudget() {
        return plafondbudget;
    }

    public void setPlafondbudget(Plafondbudget plafondbudget) {
        this.plafondbudget = plafondbudget;
    }

    public UniteCosting getUniteCosting() {
        return uniteCosting;
    }

    public void setUniteCosting(UniteCosting uniteCosting) {
        this.uniteCosting = uniteCosting;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (costingPbQtePK != null ? costingPbQtePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostingPbQte)) {
            return false;
        }
        CostingPbQte other = (CostingPbQte) object;
        if ((this.costingPbQtePK == null && other.costingPbQtePK != null) || (this.costingPbQtePK != null && !this.costingPbQtePK.equals(other.costingPbQtePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CostingPbQte[ costingPbQtePK=" + costingPbQtePK + " ]";
    }
    
}
