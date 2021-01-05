/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Embeddable
public class ContratTachePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    private long idtache;
    @Basic(optional = false)
    @NotNull
    private long idcontrat;

    public ContratTachePK() {
    }

    public ContratTachePK(long idtache, long idcontrat) {
        this.idtache = idtache;
        this.idcontrat = idcontrat;
    }

    public long getIdtache() {
        return idtache;
    }

    public void setIdtache(long idtache) {
        this.idtache = idtache;
    }

    public long getIdcontrat() {
        return idcontrat;
    }

    public void setIdcontrat(long idcontrat) {
        this.idcontrat = idcontrat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idtache;
        hash += (int) idcontrat;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratTachePK)) {
            return false;
        }
        ContratTachePK other = (ContratTachePK) object;
        if (this.idtache != other.idtache) {
            return false;
        }
        if (this.idcontrat != other.idcontrat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ContratTachePK[ idtache=" + idtache + ", idcontrat=" + idcontrat + " ]";
    }
    
}
