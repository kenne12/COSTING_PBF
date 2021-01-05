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
    @NamedQuery(name = "Moyens.findAll", query = "SELECT m FROM Moyens m"),
    @NamedQuery(name = "Moyens.findByIdmoyens", query = "SELECT m FROM Moyens m WHERE m.idmoyens = :idmoyens"),
    @NamedQuery(name = "Moyens.findByCp", query = "SELECT m FROM Moyens m WHERE m.cp = :cp"),
    @NamedQuery(name = "Moyens.findByCt", query = "SELECT m FROM Moyens m WHERE m.ct = :ct"),
    @NamedQuery(name = "Moyens.findByCu", query = "SELECT m FROM Moyens m WHERE m.cu = :cu"),
    @NamedQuery(name = "Moyens.findByQte", query = "SELECT m FROM Moyens m WHERE m.qte = :qte"),
    @NamedQuery(name = "Moyens.findByObservation", query = "SELECT m FROM Moyens m WHERE m.observation = :observation"),
    @NamedQuery(name = "Moyens.findByMontantexecute", query = "SELECT m FROM Moyens m WHERE m.montantexecute = :montantexecute"),
    @NamedQuery(name = "Moyens.findByEtat", query = "SELECT m FROM Moyens m WHERE m.etat = :etat"),
    @NamedQuery(name = "Moyens.findByDateEnregistre", query = "SELECT m FROM Moyens m WHERE m.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Moyens.findByDerniereModif", query = "SELECT m FROM Moyens m WHERE m.derniereModif = :derniereModif"),
    @NamedQuery(name = "Moyens.findByQuantite", query = "SELECT m FROM Moyens m WHERE m.quantite = :quantite"),
    @NamedQuery(name = "Moyens.findByCpanneeplus1", query = "SELECT m FROM Moyens m WHERE m.cpanneeplus1 = :cpanneeplus1"),
    @NamedQuery(name = "Moyens.findByCpanneeplus2", query = "SELECT m FROM Moyens m WHERE m.cpanneeplus2 = :cpanneeplus2"),
    @NamedQuery(name = "Moyens.findByMontantLiquide", query = "SELECT m FROM Moyens m WHERE m.montantLiquide = :montantLiquide"),
    @NamedQuery(name = "Moyens.findByMontantPaye", query = "SELECT m FROM Moyens m WHERE m.montantPaye = :montantPaye")})
public class Moyens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idmoyens;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double cp;
    private Double ct;
    private Double cu;
    private Double qte;
    private String observation;
    private Double montantexecute;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    private Integer quantite;
    private Double cpanneeplus1;
    private Double cpanneeplus2;
    @Column(name = "montant_liquide")
    private Double montantLiquide;
    @Column(name = "montant_paye")
    private Double montantPaye;
    private int numero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moyens", fetch = FetchType.LAZY)
    private Collection<ContratMoyens> contratMoyensCollection;
    @OneToMany(mappedBy = "idmoyens", fetch = FetchType.LAZY)
    private Collection<CostingContratQte> costingContratQteCollection;
    @JoinColumn(name = "idbudget", referencedColumnName = "idbudget")
    @ManyToOne(fetch = FetchType.LAZY)
    private Budget idbudget;
    @JoinColumn(name = "idimputation", referencedColumnName = "idimputation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Imputation idimputation;
    @JoinColumn(name = "idtache", referencedColumnName = "idtache")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tache idtache;

    public Moyens() {
    }

    public Moyens(Long idmoyens) {
        this.idmoyens = idmoyens;
    }

    public Long getIdmoyens() {
        return idmoyens;
    }

    public void setIdmoyens(Long idmoyens) {
        this.idmoyens = idmoyens;
    }

    public Double getCp() {
        return cp;
    }

    public void setCp(Double cp) {
        this.cp = cp;
    }

    public Double getCt() {
        return ct;
    }

    public void setCt(Double ct) {
        this.ct = ct;
    }

    public Double getCu() {
        return cu;
    }

    public void setCu(Double cu) {
        this.cu = cu;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Double getMontantexecute() {
        return montantexecute;
    }

    public void setMontantexecute(Double montantexecute) {
        this.montantexecute = montantexecute;
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

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getCpanneeplus1() {
        return cpanneeplus1;
    }

    public void setCpanneeplus1(Double cpanneeplus1) {
        this.cpanneeplus1 = cpanneeplus1;
    }

    public Double getCpanneeplus2() {
        return cpanneeplus2;
    }

    public void setCpanneeplus2(Double cpanneeplus2) {
        this.cpanneeplus2 = cpanneeplus2;
    }

    public Double getMontantLiquide() {
        return montantLiquide;
    }

    public void setMontantLiquide(Double montantLiquide) {
        this.montantLiquide = montantLiquide;
    }

    public Double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

    @XmlTransient
    public Collection<ContratMoyens> getContratMoyensCollection() {
        return contratMoyensCollection;
    }

    public void setContratMoyensCollection(Collection<ContratMoyens> contratMoyensCollection) {
        this.contratMoyensCollection = contratMoyensCollection;
    }

    @XmlTransient
    public Collection<CostingContratQte> getCostingContratQteCollection() {
        return costingContratQteCollection;
    }

    public void setCostingContratQteCollection(Collection<CostingContratQte> costingContratQteCollection) {
        this.costingContratQteCollection = costingContratQteCollection;
    }

    public Budget getIdbudget() {
        return idbudget;
    }

    public void setIdbudget(Budget idbudget) {
        this.idbudget = idbudget;
    }

    public Imputation getIdimputation() {
        return idimputation;
    }

    public void setIdimputation(Imputation idimputation) {
        this.idimputation = idimputation;
    }

    public Tache getIdtache() {
        return idtache;
    }

    public void setIdtache(Tache idtache) {
        this.idtache = idtache;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmoyens != null ? idmoyens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moyens)) {
            return false;
        }
        Moyens other = (Moyens) object;
        if ((this.idmoyens == null && other.idmoyens != null) || (this.idmoyens != null && !this.idmoyens.equals(other.idmoyens))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Moyens[ idmoyens=" + idmoyens + " ]";
    }

}
