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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByIdCompte", query = "SELECT c FROM Compte c WHERE c.idCompte = :idCompte"),
    @NamedQuery(name = "Compte.findByLogin", query = "SELECT c FROM Compte c WHERE c.login = :login"),
    @NamedQuery(name = "Compte.findByMdp", query = "SELECT c FROM Compte c WHERE c.mdp = :mdp"),
    @NamedQuery(name = "Compte.findByLangue", query = "SELECT c FROM Compte c WHERE c.langue = :langue"),
    @NamedQuery(name = "Compte.findByConnexion", query = "SELECT c FROM Compte c WHERE c.connexion = :connexion"),
    @NamedQuery(name = "Compte.findByEtat", query = "SELECT c FROM Compte c WHERE c.etat = :etat"),
    @NamedQuery(name = "Compte.findByHeurecondecon", query = "SELECT c FROM Compte c WHERE c.heurecondecon = :heurecondecon"),
    @NamedQuery(name = "Compte.findByDateEnregistre", query = "SELECT c FROM Compte c WHERE c.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Compte.findByDernieremodif", query = "SELECT c FROM Compte c WHERE c.dernieremodif = :dernieremodif")})
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_compte")
    private Long idCompte;
    @Size(max = 254)
    private String login;
    @Size(max = 254)
    private String mdp;
    @Size(max = 254)
    private String langue;
    @Size(max = 254)
    private String connexion;
    @Size(max = 254)
    private String etat;
    @Temporal(TemporalType.DATE)
    private Date heurecondecon;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Temporal(TemporalType.DATE)
    private Date dernieremodif;
    @JoinTable(name = "cpte_grpusers", joinColumns = {
        @JoinColumn(name = "id_compte", referencedColumnName = "id_compte")}, inverseJoinColumns = {
        @JoinColumn(name = "id_groupe_utilisateur", referencedColumnName = "id_groupe_utilisateur")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<GroupeUtilisateur> groupeUtilisateurCollection;
    @OneToMany(mappedBy = "idCompte", fetch = FetchType.LAZY)
    private Collection<Privilege> privilegeCollection;
    @OneToMany(mappedBy = "idCompte", fetch = FetchType.LAZY)
    private Collection<PrivilegeB> privilegeBCollection;
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur idUtilisateur;

    public Compte() {
    }

    public Compte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getConnexion() {
        return connexion;
    }

    public void setConnexion(String connexion) {
        this.connexion = connexion;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getHeurecondecon() {
        return heurecondecon;
    }

    public void setHeurecondecon(Date heurecondecon) {
        this.heurecondecon = heurecondecon;
    }

    public Date getDateEnregistre() {
        return dateEnregistre;
    }

    public void setDateEnregistre(Date dateEnregistre) {
        this.dateEnregistre = dateEnregistre;
    }

    public Date getDernieremodif() {
        return dernieremodif;
    }

    public void setDernieremodif(Date dernieremodif) {
        this.dernieremodif = dernieremodif;
    }

    @XmlTransient
    public Collection<GroupeUtilisateur> getGroupeUtilisateurCollection() {
        return groupeUtilisateurCollection;
    }

    public void setGroupeUtilisateurCollection(Collection<GroupeUtilisateur> groupeUtilisateurCollection) {
        this.groupeUtilisateurCollection = groupeUtilisateurCollection;
    }

    @XmlTransient
    public Collection<Privilege> getPrivilegeCollection() {
        return privilegeCollection;
    }

    public void setPrivilegeCollection(Collection<Privilege> privilegeCollection) {
        this.privilegeCollection = privilegeCollection;
    }

    @XmlTransient
    public Collection<PrivilegeB> getPrivilegeBCollection() {
        return privilegeBCollection;
    }

    public void setPrivilegeBCollection(Collection<PrivilegeB> privilegeBCollection) {
        this.privilegeBCollection = privilegeBCollection;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompte != null ? idCompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.idCompte == null && other.idCompte != null) || (this.idCompte != null && !this.idCompte.equals(other.idCompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compte[ idCompte=" + idCompte + " ]";
    }
    
}
