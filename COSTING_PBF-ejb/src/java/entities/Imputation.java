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
    @NamedQuery(name = "Imputation.findAll", query = "SELECT i FROM Imputation i"),
    @NamedQuery(name = "Imputation.findByIdimputation", query = "SELECT i FROM Imputation i WHERE i.idimputation = :idimputation"),
    @NamedQuery(name = "Imputation.findByCode", query = "SELECT i FROM Imputation i WHERE i.code = :code"),
    @NamedQuery(name = "Imputation.findByNom", query = "SELECT i FROM Imputation i WHERE i.nom = :nom"),
    @NamedQuery(name = "Imputation.findByDescription", query = "SELECT i FROM Imputation i WHERE i.description = :description"),
    @NamedQuery(name = "Imputation.findByNicename", query = "SELECT i FROM Imputation i WHERE i.nicename = :nicename"),
    @NamedQuery(name = "Imputation.findByCriterevalidation", query = "SELECT i FROM Imputation i WHERE i.criterevalidation = :criterevalidation"),
    @NamedQuery(name = "Imputation.findByBonus", query = "SELECT i FROM Imputation i WHERE i.bonus = :bonus"),
    @NamedQuery(name = "Imputation.findByDateEnregistre", query = "SELECT i FROM Imputation i WHERE i.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Imputation.findByDerniereModif", query = "SELECT i FROM Imputation i WHERE i.derniereModif = :derniereModif"),
    @NamedQuery(name = "Imputation.findByEtat", query = "SELECT i FROM Imputation i WHERE i.etat = :etat")})
public class Imputation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idimputation;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String description;
    @Size(max = 254)
    private String nicename;
    private String criterevalidation;
    private Boolean bonus;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @Size(max = 254)
    private String etat;
    @JoinColumn(name = "idorigine", referencedColumnName = "idorigine")
    @ManyToOne(fetch = FetchType.LAZY)
    private Origine idorigine;
    @JoinColumn(name = "idsousrubrique", referencedColumnName = "idsousrubrique")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousrubrique idsousrubrique;
    @OneToMany(mappedBy = "idimputation", fetch = FetchType.LAZY)
    private Collection<Moyens> moyensCollection;
    @OneToMany(mappedBy = "idimputation", fetch = FetchType.LAZY)
    private Collection<Modelecosting> modelecostingCollection;
    @ManyToOne
    @JoinColumn(name = "idunite", referencedColumnName = "idunite")
    private Unite idunite;
    @Column(name = "cout_unitaire")
    private double coutUnitaire;

    public Imputation() {
    }

    public Imputation(Long idimputation) {
        this.idimputation = idimputation;
    }

    public Long getIdimputation() {
        return idimputation;
    }

    public void setIdimputation(Long idimputation) {
        this.idimputation = idimputation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNicename() {
        return nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
    }

    public String getCriterevalidation() {
        return criterevalidation;
    }

    public void setCriterevalidation(String criterevalidation) {
        this.criterevalidation = criterevalidation;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public Origine getIdorigine() {
        return idorigine;
    }

    public void setIdorigine(Origine idorigine) {
        this.idorigine = idorigine;
    }

    public Sousrubrique getIdsousrubrique() {
        return idsousrubrique;
    }

    public void setIdsousrubrique(Sousrubrique idsousrubrique) {
        this.idsousrubrique = idsousrubrique;
    }

    public Unite getIdunite() {
        return idunite;
    }

    public void setIdunite(Unite idunite) {
        this.idunite = idunite;
    }

    @XmlTransient
    public Collection<Moyens> getMoyensCollection() {
        return moyensCollection;
    }

    public void setMoyensCollection(Collection<Moyens> moyensCollection) {
        this.moyensCollection = moyensCollection;
    }

    @XmlTransient
    public Collection<Modelecosting> getModelecostingCollection() {
        return modelecostingCollection;
    }

    public void setModelecostingCollection(Collection<Modelecosting> modelecostingCollection) {
        this.modelecostingCollection = modelecostingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idimputation != null ? idimputation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imputation)) {
            return false;
        }
        Imputation other = (Imputation) object;
        if ((this.idimputation == null && other.idimputation != null) || (this.idimputation != null && !this.idimputation.equals(other.idimputation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Imputation[ idimputation=" + idimputation + " ]";
    }

}
