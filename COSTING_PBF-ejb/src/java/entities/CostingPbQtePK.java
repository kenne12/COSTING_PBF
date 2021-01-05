/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Embeddable
public class CostingPbQtePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    private Long idplafondbudget;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idunite_costing")
    private int iduniteCosting;

    public CostingPbQtePK() {
    }

    public CostingPbQtePK(Long idplafondbudget, int iduniteCosting) {
        this.idplafondbudget = idplafondbudget;
        this.iduniteCosting = iduniteCosting;
    }

    public long getIdplafondbudget() {
        return idplafondbudget;
    }

    public void setIdplafondbudget(long idplafondbudget) {
        this.idplafondbudget = idplafondbudget;
    }

    public int getIduniteCosting() {
        return iduniteCosting;
    }

    public void setIduniteCosting(int iduniteCosting) {
        this.iduniteCosting = iduniteCosting;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Long) idplafondbudget;
        hash += (int) iduniteCosting;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostingPbQtePK)) {
            return false;
        }
        CostingPbQtePK other = (CostingPbQtePK) object;
        if (this.idplafondbudget != other.idplafondbudget) {
            return false;
        }
        if (this.iduniteCosting != other.iduniteCosting) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CostingPbQtePK[ idplafondbudget=" + idplafondbudget + ", iduniteCosting=" + iduniteCosting + " ]";
    }
    
}
