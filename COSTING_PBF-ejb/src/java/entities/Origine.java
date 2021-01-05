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
    @NamedQuery(name = "Origine.findAll", query = "SELECT o FROM Origine o"),
    @NamedQuery(name = "Origine.findByIdorigine", query = "SELECT o FROM Origine o WHERE o.idorigine = :idorigine"),
    @NamedQuery(name = "Origine.findByCode", query = "SELECT o FROM Origine o WHERE o.code = :code"),
    @NamedQuery(name = "Origine.findByNom", query = "SELECT o FROM Origine o WHERE o.nom = :nom")})
public class Origine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idorigine;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String nom;
    @OneToMany(mappedBy = "idorigine", fetch = FetchType.LAZY)
    private Collection<Imputation> imputationCollection;

    public Origine() {
    }

    public Origine(Integer idorigine) {
        this.idorigine = idorigine;
    }

    public Integer getIdorigine() {
        return idorigine;
    }

    public void setIdorigine(Integer idorigine) {
        this.idorigine = idorigine;
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
    public Collection<Imputation> getImputationCollection() {
        return imputationCollection;
    }

    public void setImputationCollection(Collection<Imputation> imputationCollection) {
        this.imputationCollection = imputationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorigine != null ? idorigine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Origine)) {
            return false;
        }
        Origine other = (Origine) object;
        if ((this.idorigine == null && other.idorigine != null) || (this.idorigine != null && !this.idorigine.equals(other.idorigine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Origine[ idorigine=" + idorigine + " ]";
    }
    
}
