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
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Contrat.findAll", query = "SELECT c FROM Contrat c"),
    @NamedQuery(name = "Contrat.findByIdcontrat", query = "SELECT c FROM Contrat c WHERE c.idcontrat = :idcontrat"),
    @NamedQuery(name = "Contrat.findByDateSignature", query = "SELECT c FROM Contrat c WHERE c.dateSignature = :dateSignature"),
    @NamedQuery(name = "Contrat.findByMontant", query = "SELECT c FROM Contrat c WHERE c.montant = :montant"),
    @NamedQuery(name = "Contrat.findByEtat", query = "SELECT c FROM Contrat c WHERE c.etat = :etat"),
    @NamedQuery(name = "Contrat.findByLibelle", query = "SELECT c FROM Contrat c WHERE c.libelle = :libelle"),
    @NamedQuery(name = "Contrat.findByClient", query = "SELECT c FROM Contrat c WHERE c.client = :client"),
    @NamedQuery(name = "Contrat.findByExecutant", query = "SELECT c FROM Contrat c WHERE c.executant = :executant")})
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idcontrat;
    @Column(name = "date_signature")
    @Temporal(TemporalType.DATE)
    private Date dateSignature;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double montant;
    private Boolean etat;
    private String libelle;
    private String client;
    private String executant;
    private Integer duree;
    @JoinColumn(name = "idbudget", referencedColumnName = "idbudget")
    @ManyToOne(fetch = FetchType.LAZY)
    private Budget idbudget;
    @JoinColumn(name = "idfrequence", referencedColumnName = "idfrequence")
    @ManyToOne(fetch = FetchType.LAZY)
    private Frequence idfrequence;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrat", fetch = FetchType.LAZY)
    private Collection<ContratMoyens> contratMoyensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrat", fetch = FetchType.LAZY)
    private Collection<ContratTache> contratTacheCollection;
    @OneToMany(mappedBy = "idcontrat", fetch = FetchType.LAZY)
    private Collection<CostingContratQte> costingContratQteCollection;

    public Contrat() {
    }

    public Contrat(Long idcontrat) {
        this.idcontrat = idcontrat;
    }

    public Long getIdcontrat() {
        return idcontrat;
    }

    public void setIdcontrat(Long idcontrat) {
        this.idcontrat = idcontrat;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getExecutant() {
        return executant;
    }

    public void setExecutant(String executant) {
        this.executant = executant;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Budget getIdbudget() {
        return idbudget;
    }

    public void setIdbudget(Budget idbudget) {
        this.idbudget = idbudget;
    }

    public Frequence getIdfrequence() {
        return idfrequence;
    }

    public void setIdfrequence(Frequence idfrequence) {
        this.idfrequence = idfrequence;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @XmlTransient
    public Collection<ContratMoyens> getContratMoyensCollection() {
        return contratMoyensCollection;
    }

    public void setContratMoyensCollection(Collection<ContratMoyens> contratMoyensCollection) {
        this.contratMoyensCollection = contratMoyensCollection;
    }

    @XmlTransient
    public Collection<ContratTache> getContratTacheCollection() {
        return contratTacheCollection;
    }

    public void setContratTacheCollection(Collection<ContratTache> contratTacheCollection) {
        this.contratTacheCollection = contratTacheCollection;
    }

    @XmlTransient
    public Collection<CostingContratQte> getCostingContratQteCollection() {
        return costingContratQteCollection;
    }

    public void setCostingContratQteCollection(Collection<CostingContratQte> costingContratQteCollection) {
        this.costingContratQteCollection = costingContratQteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrat != null ? idcontrat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrat)) {
            return false;
        }
        Contrat other = (Contrat) object;
        if ((this.idcontrat == null && other.idcontrat != null) || (this.idcontrat != null && !this.idcontrat.equals(other.idcontrat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contrat[ idcontrat=" + idcontrat + " ]";
    }

}
