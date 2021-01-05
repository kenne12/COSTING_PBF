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
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a"),
    @NamedQuery(name = "Action.findByIdaction", query = "SELECT a FROM Action a WHERE a.idaction = :idaction"),
    @NamedQuery(name = "Action.findByCode", query = "SELECT a FROM Action a WHERE a.code = :code"),
    @NamedQuery(name = "Action.findByNom", query = "SELECT a FROM Action a WHERE a.nom = :nom"),
    @NamedQuery(name = "Action.findByEffet", query = "SELECT a FROM Action a WHERE a.effet = :effet"),
    @NamedQuery(name = "Action.findByCadremeo", query = "SELECT a FROM Action a WHERE a.cadremeo = :cadremeo"),
    @NamedQuery(name = "Action.findByRespomeo", query = "SELECT a FROM Action a WHERE a.respomeo = :respomeo"),
    @NamedQuery(name = "Action.findByObjectifs", query = "SELECT a FROM Action a WHERE a.objectifs = :objectifs"),
    @NamedQuery(name = "Action.findByEtat", query = "SELECT a FROM Action a WHERE a.etat = :etat"),
    @NamedQuery(name = "Action.findByDateEnregistre", query = "SELECT a FROM Action a WHERE a.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Action.findByDerniereModif", query = "SELECT a FROM Action a WHERE a.derniereModif = :derniereModif"),
    @NamedQuery(name = "Action.findByIndicateur", query = "SELECT a FROM Action a WHERE a.indicateur = :indicateur"),
    @NamedQuery(name = "Action.findByCible", query = "SELECT a FROM Action a WHERE a.cible = :cible"),
    @NamedQuery(name = "Action.findByBaseline", query = "SELECT a FROM Action a WHERE a.baseline = :baseline")})
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idaction;
    @Size(max = 254)
    private String code;
    private String nom;
    @Size(max = 254)
    private String effet;
    @Size(max = 254)
    private String cadremeo;
    @Size(max = 254)
    private String respomeo;
    private String objectifs;
    @Size(max = 254)
    private String etat;
    private double montantdisponible;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @Size(max = 254)
    private String indicateur;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double cible;
    private Double baseline;
    @JoinColumn(name = "idprogramme", referencedColumnName = "idprogramme")
    @ManyToOne(fetch = FetchType.LAZY)
    private Programme idprogramme;
    @OneToMany(mappedBy = "idaction", fetch = FetchType.LAZY)
    private Collection<Activite> activiteCollection;

    public Action() {
    }

    public Action(Long idaction) {
        this.idaction = idaction;
    }

    public Long getIdaction() {
        return idaction;
    }

    public void setIdaction(Long idaction) {
        this.idaction = idaction;
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

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public String getCadremeo() {
        return cadremeo;
    }

    public void setCadremeo(String cadremeo) {
        this.cadremeo = cadremeo;
    }

    public String getRespomeo() {
        return respomeo;
    }

    public void setRespomeo(String respomeo) {
        this.respomeo = respomeo;
    }

    public String getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(String objectifs) {
        this.objectifs = objectifs;
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

    public String getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(String indicateur) {
        this.indicateur = indicateur;
    }

    public Double getCible() {
        return cible;
    }

    public void setCible(Double cible) {
        this.cible = cible;
    }

    public Double getBaseline() {
        return baseline;
    }

    public void setBaseline(Double baseline) {
        this.baseline = baseline;
    }

    public Programme getIdprogramme() {
        return idprogramme;
    }

    public void setIdprogramme(Programme idprogramme) {
        this.idprogramme = idprogramme;
    }

    @XmlTransient
    public Collection<Activite> getActiviteCollection() {
        return activiteCollection;
    }

    public void setActiviteCollection(Collection<Activite> activiteCollection) {
        this.activiteCollection = activiteCollection;
    }

    public double getMontantdisponible() {
        return montantdisponible;
    }

    public void setMontantdisponible(double montantdisponible) {
        this.montantdisponible = montantdisponible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaction != null ? idaction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.idaction == null && other.idaction != null) || (this.idaction != null && !this.idaction.equals(other.idaction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Action[ idaction=" + idaction + " ]";
    }

}
