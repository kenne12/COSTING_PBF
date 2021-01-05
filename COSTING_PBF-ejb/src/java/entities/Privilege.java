/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p"),
    @NamedQuery(name = "Privilege.findByIdPrivilege", query = "SELECT p FROM Privilege p WHERE p.idPrivilege = :idPrivilege"),
    @NamedQuery(name = "Privilege.findByConfiguration", query = "SELECT p FROM Privilege p WHERE p.configuration = :configuration"),
    @NamedQuery(name = "Privilege.findByEtat", query = "SELECT p FROM Privilege p WHERE p.etat = :etat"),
    @NamedQuery(name = "Privilege.findByDateEnregistre", query = "SELECT p FROM Privilege p WHERE p.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Privilege.findByDerniereModif", query = "SELECT p FROM Privilege p WHERE p.derniereModif = :derniereModif")})
public class Privilege implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_privilege")
    private Long idPrivilege;
    @Size(max = 254)
    private String configuration;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @JoinColumn(name = "id_compte", referencedColumnName = "id_compte")
    @ManyToOne(fetch = FetchType.LAZY)
    private Compte idCompte;
    @JoinColumn(name = "id_groupe_utilisateur", referencedColumnName = "id_groupe_utilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private GroupeUtilisateur idGroupeUtilisateur;

    public Privilege() {
    }

    public Privilege(Long idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public Long getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(Long idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateEnregistre() {
        return dateEnregistre;
    }

    public void setDateEnregistre(Date dateEnregistre) {
        this.dateEnregistre = dateEnregistre;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public Compte getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Compte idCompte) {
        this.idCompte = idCompte;
    }

    public GroupeUtilisateur getIdGroupeUtilisateur() {
        return idGroupeUtilisateur;
    }

    public void setIdGroupeUtilisateur(GroupeUtilisateur idGroupeUtilisateur) {
        this.idGroupeUtilisateur = idGroupeUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivilege != null ? idPrivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.idPrivilege == null && other.idPrivilege != null) || (this.idPrivilege != null && !this.idPrivilege.equals(other.idPrivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Privilege[ idPrivilege=" + idPrivilege + " ]";
    }
    
}
