/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "costing_contrat_qte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CostingContratQte.findAll", query = "SELECT c FROM CostingContratQte c"),
    @NamedQuery(name = "CostingContratQte.findByIdcostingContratQte", query = "SELECT c FROM CostingContratQte c WHERE c.idcostingContratQte = :idcostingContratQte"),
    @NamedQuery(name = "CostingContratQte.findByQuantite", query = "SELECT c FROM CostingContratQte c WHERE c.quantite = :quantite")})
public class CostingContratQte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcosting_contrat_qte")
    private Long idcostingContratQte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double quantite;
    private boolean etat;
    @JoinColumn(name = "idcontrat", referencedColumnName = "idcontrat")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contrat idcontrat;
    @JoinColumn(name = "idmoyens", referencedColumnName = "idmoyens")
    @ManyToOne(fetch = FetchType.LAZY)
    private Moyens idmoyens;
    @JoinColumn(name = "idunite_costing", referencedColumnName = "idunite_costing")
    @ManyToOne(fetch = FetchType.LAZY)
    private UniteCosting iduniteCosting;

    public CostingContratQte() {
    }

    public CostingContratQte(Long idcostingContratQte) {
        this.idcostingContratQte = idcostingContratQte;
    }

    public Long getIdcostingContratQte() {
        return idcostingContratQte;
    }

    public void setIdcostingContratQte(Long idcostingContratQte) {
        this.idcostingContratQte = idcostingContratQte;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Contrat getIdcontrat() {
        return idcontrat;
    }

    public void setIdcontrat(Contrat idcontrat) {
        this.idcontrat = idcontrat;
    }

    public Moyens getIdmoyens() {
        return idmoyens;
    }

    public void setIdmoyens(Moyens idmoyens) {
        this.idmoyens = idmoyens;
    }

    public UniteCosting getIduniteCosting() {
        return iduniteCosting;
    }

    public void setIduniteCosting(UniteCosting iduniteCosting) {
        this.iduniteCosting = iduniteCosting;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcostingContratQte != null ? idcostingContratQte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostingContratQte)) {
            return false;
        }
        CostingContratQte other = (CostingContratQte) object;
        if ((this.idcostingContratQte == null && other.idcostingContratQte != null) || (this.idcostingContratQte != null && !this.idcostingContratQte.equals(other.idcostingContratQte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CostingContratQte[ idcostingContratQte=" + idcostingContratQte + " ]";
    }

}
