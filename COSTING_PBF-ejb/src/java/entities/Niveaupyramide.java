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
    @NamedQuery(name = "Niveaupyramide.findAll", query = "SELECT n FROM Niveaupyramide n"),
    @NamedQuery(name = "Niveaupyramide.findByIdniveaupyramide", query = "SELECT n FROM Niveaupyramide n WHERE n.idniveaupyramide = :idniveaupyramide"),
    @NamedQuery(name = "Niveaupyramide.findByCode", query = "SELECT n FROM Niveaupyramide n WHERE n.code = :code"),
    @NamedQuery(name = "Niveaupyramide.findByNom", query = "SELECT n FROM Niveaupyramide n WHERE n.nom = :nom"),
    @NamedQuery(name = "Niveaupyramide.findByEtat", query = "SELECT n FROM Niveaupyramide n WHERE n.etat = :etat"),
    @NamedQuery(name = "Niveaupyramide.findByDateEnregistre", query = "SELECT n FROM Niveaupyramide n WHERE n.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Niveaupyramide.findByDerniereModif", query = "SELECT n FROM Niveaupyramide n WHERE n.derniereModif = :derniereModif")})
public class Niveaupyramide implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idniveaupyramide;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @OneToMany(mappedBy = "idniveaupyramide", fetch = FetchType.LAZY)
    private Collection<Pyramide> pyramideCollection;

    public Niveaupyramide() {
    }

    public Niveaupyramide(Long idniveaupyramide) {
        this.idniveaupyramide = idniveaupyramide;
    }

    public Long getIdniveaupyramide() {
        return idniveaupyramide;
    }

    public void setIdniveaupyramide(Long idniveaupyramide) {
        this.idniveaupyramide = idniveaupyramide;
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
    public Collection<Pyramide> getPyramideCollection() {
        return pyramideCollection;
    }

    public void setPyramideCollection(Collection<Pyramide> pyramideCollection) {
        this.pyramideCollection = pyramideCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idniveaupyramide != null ? idniveaupyramide.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Niveaupyramide)) {
            return false;
        }
        Niveaupyramide other = (Niveaupyramide) object;
        if ((this.idniveaupyramide == null && other.idniveaupyramide != null) || (this.idniveaupyramide != null && !this.idniveaupyramide.equals(other.idniveaupyramide))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Niveaupyramide[ idniveaupyramide=" + idniveaupyramide + " ]";
    }
    
}
