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
@Table(name = "privilege_b")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrivilegeB.findAll", query = "SELECT p FROM PrivilegeB p"),
    @NamedQuery(name = "PrivilegeB.findByIdprivilegeB", query = "SELECT p FROM PrivilegeB p WHERE p.idprivilegeB = :idprivilegeB")})
public class PrivilegeB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idprivilege_b")
    private Long idprivilegeB;
    @JoinColumn(name = "id_compte", referencedColumnName = "id_compte")
    @ManyToOne(fetch = FetchType.LAZY)
    private Compte idCompte;
    @JoinColumn(name = "idmenu_b", referencedColumnName = "idmenu_b")
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuB idmenuB;

    public PrivilegeB() {
    }

    public PrivilegeB(Long idprivilegeB) {
        this.idprivilegeB = idprivilegeB;
    }

    public Long getIdprivilegeB() {
        return idprivilegeB;
    }

    public void setIdprivilegeB(Long idprivilegeB) {
        this.idprivilegeB = idprivilegeB;
    }

    public Compte getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Compte idCompte) {
        this.idCompte = idCompte;
    }

    public MenuB getIdmenuB() {
        return idmenuB;
    }

    public void setIdmenuB(MenuB idmenuB) {
        this.idmenuB = idmenuB;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprivilegeB != null ? idprivilegeB.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrivilegeB)) {
            return false;
        }
        PrivilegeB other = (PrivilegeB) object;
        if ((this.idprivilegeB == null && other.idprivilegeB != null) || (this.idprivilegeB != null && !this.idprivilegeB.equals(other.idprivilegeB))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrivilegeB[ idprivilegeB=" + idprivilegeB + " ]";
    }
    
}
