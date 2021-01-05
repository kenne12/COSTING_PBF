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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "contrat_tache")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratTache.findAll", query = "SELECT c FROM ContratTache c"),
    @NamedQuery(name = "ContratTache.findByIdtache", query = "SELECT c FROM ContratTache c WHERE c.contratTachePK.idtache = :idtache"),
    @NamedQuery(name = "ContratTache.findByIdcontrat", query = "SELECT c FROM ContratTache c WHERE c.contratTachePK.idcontrat = :idcontrat"),
    @NamedQuery(name = "ContratTache.findByMois1", query = "SELECT c FROM ContratTache c WHERE c.mois1 = :mois1"),
    @NamedQuery(name = "ContratTache.findByMois2", query = "SELECT c FROM ContratTache c WHERE c.mois2 = :mois2"),
    @NamedQuery(name = "ContratTache.findByMois3", query = "SELECT c FROM ContratTache c WHERE c.mois3 = :mois3")})
public class ContratTache implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContratTachePK contratTachePK;
    @Size(max = 254)
    @Column(name = "mois_1")
    private String mois1;
    @Size(max = 254)
    @Column(name = "mois_2")
    private String mois2;
    @Size(max = 254)
    @Column(name = "mois_3")
    private String mois3;
    private boolean etat;
    @JoinColumn(name = "idcontrat", referencedColumnName = "idcontrat", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Contrat contrat;
    @JoinColumn(name = "idtache", referencedColumnName = "idtache", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tache tache;

    public ContratTache() {
    }

    public ContratTache(ContratTachePK contratTachePK) {
        this.contratTachePK = contratTachePK;
    }

    public ContratTache(long idtache, long idcontrat) {
        this.contratTachePK = new ContratTachePK(idtache, idcontrat);
    }

    public ContratTachePK getContratTachePK() {
        return contratTachePK;
    }

    public void setContratTachePK(ContratTachePK contratTachePK) {
        this.contratTachePK = contratTachePK;
    }

    public String getMois1() {
        return mois1;
    }

    public void setMois1(String mois1) {
        this.mois1 = mois1;
    }

    public String getMois2() {
        return mois2;
    }

    public void setMois2(String mois2) {
        this.mois2 = mois2;
    }

    public String getMois3() {
        return mois3;
    }

    public void setMois3(String mois3) {
        this.mois3 = mois3;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratTachePK != null ? contratTachePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratTache)) {
            return false;
        }
        ContratTache other = (ContratTache) object;
        if ((this.contratTachePK == null && other.contratTachePK != null) || (this.contratTachePK != null && !this.contratTachePK.equals(other.contratTachePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ContratTache[ contratTachePK=" + contratTachePK + " ]";
    }

}
