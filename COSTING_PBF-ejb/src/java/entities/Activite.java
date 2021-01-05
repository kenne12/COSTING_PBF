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
    @NamedQuery(name = "Activite.findAll", query = "SELECT a FROM Activite a"),
    @NamedQuery(name = "Activite.findByIdactivite", query = "SELECT a FROM Activite a WHERE a.idactivite = :idactivite"),
    @NamedQuery(name = "Activite.findByCode", query = "SELECT a FROM Activite a WHERE a.code = :code"),
    @NamedQuery(name = "Activite.findByNom", query = "SELECT a FROM Activite a WHERE a.nom = :nom"),
    @NamedQuery(name = "Activite.findByObjectif", query = "SELECT a FROM Activite a WHERE a.objectif = :objectif"),
    @NamedQuery(name = "Activite.findByPrio", query = "SELECT a FROM Activite a WHERE a.prio = :prio"),
    @NamedQuery(name = "Activite.findByPartisprenantes", query = "SELECT a FROM Activite a WHERE a.partisprenantes = :partisprenantes"),
    @NamedQuery(name = "Activite.findByAutreconcerne", query = "SELECT a FROM Activite a WHERE a.autreconcerne = :autreconcerne"),
    @NamedQuery(name = "Activite.findByCumulextrants", query = "SELECT a FROM Activite a WHERE a.cumulextrants = :cumulextrants"),
    @NamedQuery(name = "Activite.findByResponsables", query = "SELECT a FROM Activite a WHERE a.responsables = :responsables"),
    @NamedQuery(name = "Activite.findByJustification", query = "SELECT a FROM Activite a WHERE a.justification = :justification"),
    @NamedQuery(name = "Activite.findByEtat", query = "SELECT a FROM Activite a WHERE a.etat = :etat"),
    @NamedQuery(name = "Activite.findByDateEnregistre", query = "SELECT a FROM Activite a WHERE a.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Activite.findByDerniereModif", query = "SELECT a FROM Activite a WHERE a.derniereModif = :derniereModif"),
    @NamedQuery(name = "Activite.findByCumulindicateurs", query = "SELECT a FROM Activite a WHERE a.cumulindicateurs = :cumulindicateurs")})
public class Activite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idactivite;
    @Size(max = 254)
    private String code;
    private String nom;
    private String objectif;
    private Boolean prio;
    @Size(max = 254)
    private String partisprenantes;
    @Size(max = 254)
    private String autreconcerne;
    @Size(max = 254)
    private String cumulextrants;
    @Size(max = 254)
    private String responsables;
    private String justification;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @Size(max = 254)
    private String cumulindicateurs;
    @OneToMany(mappedBy = "idactivite", fetch = FetchType.LAZY)
    private Collection<Tache> tacheCollection;
    @JoinColumn(name = "idaction", referencedColumnName = "idaction")
    @ManyToOne(fetch = FetchType.LAZY)
    private Action idaction;
    @JoinColumn(name = "idrang", referencedColumnName = "idrang")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rang idrang;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;
    @JoinColumn(name = "idtypeactivite", referencedColumnName = "idtypeactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeactivite idtypeactivite;

    public Activite() {
    }

    public Activite(Long idactivite) {
        this.idactivite = idactivite;
    }

    public Long getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Long idactivite) {
        this.idactivite = idactivite;
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

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Boolean getPrio() {
        return prio;
    }

    public void setPrio(Boolean prio) {
        this.prio = prio;
    }

    public String getPartisprenantes() {
        return partisprenantes;
    }

    public void setPartisprenantes(String partisprenantes) {
        this.partisprenantes = partisprenantes;
    }

    public String getAutreconcerne() {
        return autreconcerne;
    }

    public void setAutreconcerne(String autreconcerne) {
        this.autreconcerne = autreconcerne;
    }

    public String getCumulextrants() {
        return cumulextrants;
    }

    public void setCumulextrants(String cumulextrants) {
        this.cumulextrants = cumulextrants;
    }

    public String getResponsables() {
        return responsables;
    }

    public void setResponsables(String responsables) {
        this.responsables = responsables;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
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

    public String getCumulindicateurs() {
        return cumulindicateurs;
    }

    public void setCumulindicateurs(String cumulindicateurs) {
        this.cumulindicateurs = cumulindicateurs;
    }

    @XmlTransient
    public Collection<Tache> getTacheCollection() {
        return tacheCollection;
    }

    public void setTacheCollection(Collection<Tache> tacheCollection) {
        this.tacheCollection = tacheCollection;
    }

    public Action getIdaction() {
        return idaction;
    }

    public void setIdaction(Action idaction) {
        this.idaction = idaction;
    }

    public Rang getIdrang() {
        return idrang;
    }

    public void setIdrang(Rang idrang) {
        this.idrang = idrang;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    public Typeactivite getIdtypeactivite() {
        return idtypeactivite;
    }

    public void setIdtypeactivite(Typeactivite idtypeactivite) {
        this.idtypeactivite = idtypeactivite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactivite != null ? idactivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activite)) {
            return false;
        }
        Activite other = (Activite) object;
        if ((this.idactivite == null && other.idactivite != null) || (this.idactivite != null && !this.idactivite.equals(other.idactivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Activite[ idactivite=" + idactivite + " ]";
    }
    
}
