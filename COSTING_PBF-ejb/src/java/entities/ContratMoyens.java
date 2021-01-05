/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "contrat_moyens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratMoyens.findAll", query = "SELECT c FROM ContratMoyens c"),
    @NamedQuery(name = "ContratMoyens.findByIdcontrat", query = "SELECT c FROM ContratMoyens c WHERE c.contratMoyensPK.idcontrat = :idcontrat"),
    @NamedQuery(name = "ContratMoyens.findByIdmoyens", query = "SELECT c FROM ContratMoyens c WHERE c.contratMoyensPK.idmoyens = :idmoyens"),
    @NamedQuery(name = "ContratMoyens.findByQuantite", query = "SELECT c FROM ContratMoyens c WHERE c.quantite = :quantite"),
    @NamedQuery(name = "ContratMoyens.findByCu", query = "SELECT c FROM ContratMoyens c WHERE c.cu = :cu"),
    @NamedQuery(name = "ContratMoyens.findByCt", query = "SELECT c FROM ContratMoyens c WHERE c.ct = :ct")})
public class ContratMoyens implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContratMoyensPK contratMoyensPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double quantite;
    private Double cu;
    private Double ct;
    private String observation;
    private boolean etat;
    @Column(name = "observation_auto")
    private String observationAuto;
    @JoinColumn(name = "idcontrat", referencedColumnName = "idcontrat", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Contrat contrat;
    @JoinColumn(name = "idmoyens", referencedColumnName = "idmoyens", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Moyens moyens;

    public ContratMoyens() {
    }

    public ContratMoyens(ContratMoyensPK contratMoyensPK) {
        this.contratMoyensPK = contratMoyensPK;
    }

    public ContratMoyens(long idcontrat, long idmoyens) {
        this.contratMoyensPK = new ContratMoyensPK(idcontrat, idmoyens);
    }

    public ContratMoyensPK getContratMoyensPK() {
        return contratMoyensPK;
    }

    public void setContratMoyensPK(ContratMoyensPK contratMoyensPK) {
        this.contratMoyensPK = contratMoyensPK;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getCu() {
        return cu;
    }

    public void setCu(Double cu) {
        this.cu = cu;
    }

    public Double getCt() {
        return ct;
    }

    public void setCt(Double ct) {
        this.ct = ct;
    }

    public String getObservationAuto() {
        return observationAuto;
    }

    public void setObservationAuto(String observationAuto) {
        this.observationAuto = observationAuto;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Moyens getMoyens() {
        return moyens;
    }

    public void setMoyens(Moyens moyens) {
        this.moyens = moyens;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratMoyensPK != null ? contratMoyensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratMoyens)) {
            return false;
        }
        ContratMoyens other = (ContratMoyens) object;
        if ((this.contratMoyensPK == null && other.contratMoyensPK != null) || (this.contratMoyensPK != null && !this.contratMoyensPK.equals(other.contratMoyensPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ContratMoyens[ contratMoyensPK=" + contratMoyensPK + " ]";
    }

}
