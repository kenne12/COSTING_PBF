/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "groupe_utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupeUtilisateur.findAll", query = "SELECT g FROM GroupeUtilisateur g"),
    @NamedQuery(name = "GroupeUtilisateur.findByIdGroupeUtilisateur", query = "SELECT g FROM GroupeUtilisateur g WHERE g.idGroupeUtilisateur = :idGroupeUtilisateur"),
    @NamedQuery(name = "GroupeUtilisateur.findByLibelle", query = "SELECT g FROM GroupeUtilisateur g WHERE g.libelle = :libelle"),
    @NamedQuery(name = "GroupeUtilisateur.findByEtat", query = "SELECT g FROM GroupeUtilisateur g WHERE g.etat = :etat"),
    @NamedQuery(name = "GroupeUtilisateur.findByDateEnregistre", query = "SELECT g FROM GroupeUtilisateur g WHERE g.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "GroupeUtilisateur.findByDerniereModif", query = "SELECT g FROM GroupeUtilisateur g WHERE g.derniereModif = :derniereModif")})
public class GroupeUtilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_groupe_utilisateur")
    private Long idGroupeUtilisateur;
    @Size(max = 254)
    private String libelle;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @ManyToMany(mappedBy = "groupeUtilisateurCollection", fetch = FetchType.LAZY)
    private Collection<Compte> compteCollection;
    @OneToMany(mappedBy = "idGroupeUtilisateur", fetch = FetchType.LAZY)
    private Collection<Privilege> privilegeCollection;

    public GroupeUtilisateur() {
    }

    public GroupeUtilisateur(Long idGroupeUtilisateur) {
        this.idGroupeUtilisateur = idGroupeUtilisateur;
    }

    public Long getIdGroupeUtilisateur() {
        return idGroupeUtilisateur;
    }

    public void setIdGroupeUtilisateur(Long idGroupeUtilisateur) {
        this.idGroupeUtilisateur = idGroupeUtilisateur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    @XmlTransient
    public Collection<Compte> getCompteCollection() {
        return compteCollection;
    }

    public void setCompteCollection(Collection<Compte> compteCollection) {
        this.compteCollection = compteCollection;
    }

    @XmlTransient
    public Collection<Privilege> getPrivilegeCollection() {
        return privilegeCollection;
    }

    public void setPrivilegeCollection(Collection<Privilege> privilegeCollection) {
        this.privilegeCollection = privilegeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroupeUtilisateur != null ? idGroupeUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupeUtilisateur)) {
            return false;
        }
        GroupeUtilisateur other = (GroupeUtilisateur) object;
        if ((this.idGroupeUtilisateur == null && other.idGroupeUtilisateur != null) || (this.idGroupeUtilisateur != null && !this.idGroupeUtilisateur.equals(other.idGroupeUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GroupeUtilisateur[ idGroupeUtilisateur=" + idGroupeUtilisateur + " ]";
    }
    
}
