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
    @NamedQuery(name = "Programme.findAll", query = "SELECT p FROM Programme p"),
    @NamedQuery(name = "Programme.findByIdprogramme", query = "SELECT p FROM Programme p WHERE p.idprogramme = :idprogramme"),
    @NamedQuery(name = "Programme.findByCode", query = "SELECT p FROM Programme p WHERE p.code = :code"),
    @NamedQuery(name = "Programme.findByNom", query = "SELECT p FROM Programme p WHERE p.nom = :nom"),
    @NamedQuery(name = "Programme.findByStrategieprogramme", query = "SELECT p FROM Programme p WHERE p.strategieprogramme = :strategieprogramme"),
    @NamedQuery(name = "Programme.findByRespomeo", query = "SELECT p FROM Programme p WHERE p.respomeo = :respomeo"),
    @NamedQuery(name = "Programme.findByCadremeo", query = "SELECT p FROM Programme p WHERE p.cadremeo = :cadremeo"),
    @NamedQuery(name = "Programme.findByImpact", query = "SELECT p FROM Programme p WHERE p.impact = :impact"),
    @NamedQuery(name = "Programme.findByEtat", query = "SELECT p FROM Programme p WHERE p.etat = :etat"),
    @NamedQuery(name = "Programme.findByObjectifs", query = "SELECT p FROM Programme p WHERE p.objectifs = :objectifs"),
    @NamedQuery(name = "Programme.findByObjectifstrategique", query = "SELECT p FROM Programme p WHERE p.objectifstrategique = :objectifstrategique"),
    @NamedQuery(name = "Programme.findByFonction", query = "SELECT p FROM Programme p WHERE p.fonction = :fonction"),
    @NamedQuery(name = "Programme.findByIndicateur", query = "SELECT p FROM Programme p WHERE p.indicateur = :indicateur"),
    @NamedQuery(name = "Programme.findByBaseline", query = "SELECT p FROM Programme p WHERE p.baseline = :baseline"),
    @NamedQuery(name = "Programme.findByCible", query = "SELECT p FROM Programme p WHERE p.cible = :cible"),
    @NamedQuery(name = "Programme.findByDateEnregistre", query = "SELECT p FROM Programme p WHERE p.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Programme.findByDerniereModif", query = "SELECT p FROM Programme p WHERE p.derniereModif = :derniereModif")})
public class Programme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idprogramme;
    @Size(max = 254)
    private String code;
    private String nom;
    @Size(max = 254)
    private String strategieprogramme;
    @Size(max = 254)
    private String respomeo;
    @Size(max = 254)
    private String cadremeo;
    @Size(max = 254)
    private String impact;
    @Size(max = 254)
    private String etat;
    private String objectifs;
    private String objectifstrategique;
    @Size(max = 254)
    private String fonction;
    @Size(max = 254)
    private String indicateur;
    private double montantdisponible;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double baseline;
    private Double cible;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @OneToMany(mappedBy = "idprogramme", fetch = FetchType.LAZY)
    private Collection<Action> actionCollection;
    @JoinColumn(name = "idaxestrategique", referencedColumnName = "idaxestrategique")
    @ManyToOne(fetch = FetchType.LAZY)
    private Axestrategique idaxestrategique;
    @JoinColumn(name = "idinstitution", referencedColumnName = "idinstitution")
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution idinstitution;
    @JoinColumn(name = "idsoussecteur", referencedColumnName = "idsoussecteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Soussecteur idsoussecteur;

    public Programme() {
    }

    public Programme(Long idprogramme) {
        this.idprogramme = idprogramme;
    }

    public Long getIdprogramme() {
        return idprogramme;
    }

    public void setIdprogramme(Long idprogramme) {
        this.idprogramme = idprogramme;
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

    public String getStrategieprogramme() {
        return strategieprogramme;
    }

    public void setStrategieprogramme(String strategieprogramme) {
        this.strategieprogramme = strategieprogramme;
    }

    public String getRespomeo() {
        return respomeo;
    }

    public void setRespomeo(String respomeo) {
        this.respomeo = respomeo;
    }

    public String getCadremeo() {
        return cadremeo;
    }

    public void setCadremeo(String cadremeo) {
        this.cadremeo = cadremeo;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(String objectifs) {
        this.objectifs = objectifs;
    }

    public String getObjectifstrategique() {
        return objectifstrategique;
    }

    public void setObjectifstrategique(String objectifstrategique) {
        this.objectifstrategique = objectifstrategique;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(String indicateur) {
        this.indicateur = indicateur;
    }

    public Double getBaseline() {
        return baseline;
    }

    public void setBaseline(Double baseline) {
        this.baseline = baseline;
    }

    public Double getCible() {
        return cible;
    }

    public void setCible(Double cible) {
        this.cible = cible;
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

    public double getMontantdisponible() {
        return montantdisponible;
    }

    public void setMontantdisponible(double montantdisponible) {
        this.montantdisponible = montantdisponible;
    }

    @XmlTransient
    public Collection<Action> getActionCollection() {
        return actionCollection;
    }

    public void setActionCollection(Collection<Action> actionCollection) {
        this.actionCollection = actionCollection;
    }

    public Axestrategique getIdaxestrategique() {
        return idaxestrategique;
    }

    public void setIdaxestrategique(Axestrategique idaxestrategique) {
        this.idaxestrategique = idaxestrategique;
    }

    public Institution getIdinstitution() {
        return idinstitution;
    }

    public void setIdinstitution(Institution idinstitution) {
        this.idinstitution = idinstitution;
    }

    public Soussecteur getIdsoussecteur() {
        return idsoussecteur;
    }

    public void setIdsoussecteur(Soussecteur idsoussecteur) {
        this.idsoussecteur = idsoussecteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprogramme != null ? idprogramme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programme)) {
            return false;
        }
        Programme other = (Programme) object;
        if ((this.idprogramme == null && other.idprogramme != null) || (this.idprogramme != null && !this.idprogramme.equals(other.idprogramme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Programme[ idprogramme=" + idprogramme + " ]";
    }

}
