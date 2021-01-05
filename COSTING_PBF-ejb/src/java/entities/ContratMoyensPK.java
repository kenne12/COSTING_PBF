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
public class ContratMoyensPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    private long idcontrat;
    @Basic(optional = false)
    @NotNull
    private long idmoyens;

    public ContratMoyensPK() {
    }

    public ContratMoyensPK(long idcontrat, long idmoyens) {
        this.idcontrat = idcontrat;
        this.idmoyens = idmoyens;
    }

    public long getIdcontrat() {
        return idcontrat;
    }

    public void setIdcontrat(long idcontrat) {
        this.idcontrat = idcontrat;
    }

    public long getIdmoyens() {
        return idmoyens;
    }

    public void setIdmoyens(long idmoyens) {
        this.idmoyens = idmoyens;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcontrat;
        hash += (int) idmoyens;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratMoyensPK)) {
            return false;
        }
        ContratMoyensPK other = (ContratMoyensPK) object;
        if (this.idcontrat != other.idcontrat) {
            return false;
        }
        if (this.idmoyens != other.idmoyens) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ContratMoyensPK[ idcontrat=" + idcontrat + ", idmoyens=" + idmoyens + " ]";
    }
    
}
