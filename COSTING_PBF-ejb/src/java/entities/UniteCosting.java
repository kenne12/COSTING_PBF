/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "unite_costing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniteCosting.findAll", query = "SELECT u FROM UniteCosting u"),
    @NamedQuery(name = "UniteCosting.findByIduniteCosting", query = "SELECT u FROM UniteCosting u WHERE u.iduniteCosting = :iduniteCosting"),
    @NamedQuery(name = "UniteCosting.findByCode", query = "SELECT u FROM UniteCosting u WHERE u.code = :code"),
    @NamedQuery(name = "UniteCosting.findByNom", query = "SELECT u FROM UniteCosting u WHERE u.nom = :nom")})
public class UniteCosting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idunite_costing")
    private Integer iduniteCosting;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String nom;
    @OneToMany(mappedBy = "iduniteCosting", fetch = FetchType.LAZY)
    private Collection<CostingContratQte> costingContratQteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniteCosting", fetch = FetchType.LAZY)
    private Collection<CostingPbQte> costingPbQteCollection;

    public UniteCosting() {
    }

    public UniteCosting(Integer iduniteCosting) {
        this.iduniteCosting = iduniteCosting;
    }

    public Integer getIduniteCosting() {
        return iduniteCosting;
    }

    public void setIduniteCosting(Integer iduniteCosting) {
        this.iduniteCosting = iduniteCosting;
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
    public Collection<CostingContratQte> getCostingContratQteCollection() {
        return costingContratQteCollection;
    }

    public void setCostingContratQteCollection(Collection<CostingContratQte> costingContratQteCollection) {
        this.costingContratQteCollection = costingContratQteCollection;
    }

    @XmlTransient
    public Collection<CostingPbQte> getCostingPbQteCollection() {
        return costingPbQteCollection;
    }

    public void setCostingPbQteCollection(Collection<CostingPbQte> costingPbQteCollection) {
        this.costingPbQteCollection = costingPbQteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduniteCosting != null ? iduniteCosting.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UniteCosting)) {
            return false;
        }
        UniteCosting other = (UniteCosting) object;
        if ((this.iduniteCosting == null && other.iduniteCosting != null) || (this.iduniteCosting != null && !this.iduniteCosting.equals(other.iduniteCosting))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UniteCosting[ iduniteCosting=" + iduniteCosting + " ]";
    }
    
}
