/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "menu_b")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuB.findAll", query = "SELECT m FROM MenuB m"),
    @NamedQuery(name = "MenuB.findByIdmenuB", query = "SELECT m FROM MenuB m WHERE m.idmenuB = :idmenuB"),
    @NamedQuery(name = "MenuB.findByNom", query = "SELECT m FROM MenuB m WHERE m.nom = :nom"),
    @NamedQuery(name = "MenuB.findByRessource", query = "SELECT m FROM MenuB m WHERE m.ressource = :ressource")})
public class MenuB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmenu_b")
    private Integer idmenuB;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String ressource;
    @OneToMany(mappedBy = "idmenuB", fetch = FetchType.LAZY)
    private Collection<PrivilegeB> privilegeBCollection;

    public MenuB() {
    }

    public MenuB(Integer idmenuB) {
        this.idmenuB = idmenuB;
    }

    public Integer getIdmenuB() {
        return idmenuB;
    }

    public void setIdmenuB(Integer idmenuB) {
        this.idmenuB = idmenuB;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    @XmlTransient
    public Collection<PrivilegeB> getPrivilegeBCollection() {
        return privilegeBCollection;
    }

    public void setPrivilegeBCollection(Collection<PrivilegeB> privilegeBCollection) {
        this.privilegeBCollection = privilegeBCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenuB != null ? idmenuB.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuB)) {
            return false;
        }
        MenuB other = (MenuB) object;
        if ((this.idmenuB == null && other.idmenuB != null) || (this.idmenuB != null && !this.idmenuB.equals(other.idmenuB))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MenuB[ idmenuB=" + idmenuB + " ]";
    }
    
}
