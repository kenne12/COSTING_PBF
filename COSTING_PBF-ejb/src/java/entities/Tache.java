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
    @NamedQuery(name = "Tache.findAll", query = "SELECT t FROM Tache t"),
    @NamedQuery(name = "Tache.findByIdtache", query = "SELECT t FROM Tache t WHERE t.idtache = :idtache"),
    @NamedQuery(name = "Tache.findByNumordre", query = "SELECT t FROM Tache t WHERE t.numordre = :numordre"),
    @NamedQuery(name = "Tache.findByTotalmontantaloue", query = "SELECT t FROM Tache t WHERE t.totalmontantaloue = :totalmontantaloue"),
    @NamedQuery(name = "Tache.findByMoyensnecessaires", query = "SELECT t FROM Tache t WHERE t.moyensnecessaires = :moyensnecessaires"),
    @NamedQuery(name = "Tache.findByIndicateurs2014", query = "SELECT t FROM Tache t WHERE t.indicateurs2014 = :indicateurs2014"),
    @NamedQuery(name = "Tache.findByNom", query = "SELECT t FROM Tache t WHERE t.nom = :nom"),
    @NamedQuery(name = "Tache.findByEtat", query = "SELECT t FROM Tache t WHERE t.etat = :etat"),
    @NamedQuery(name = "Tache.findByDateEnregistre", query = "SELECT t FROM Tache t WHERE t.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Tache.findByDerniereModif", query = "SELECT t FROM Tache t WHERE t.derniereModif = :derniereModif"),
    @NamedQuery(name = "Tache.findByIndicateurspoursuivis", query = "SELECT t FROM Tache t WHERE t.indicateurspoursuivis = :indicateurspoursuivis"),
    @NamedQuery(name = "Tache.findByM1", query = "SELECT t FROM Tache t WHERE t.m1 = :m1"),
    @NamedQuery(name = "Tache.findByM2", query = "SELECT t FROM Tache t WHERE t.m2 = :m2"),
    @NamedQuery(name = "Tache.findByM3", query = "SELECT t FROM Tache t WHERE t.m3 = :m3"),
    @NamedQuery(name = "Tache.findByM4", query = "SELECT t FROM Tache t WHERE t.m4 = :m4"),
    @NamedQuery(name = "Tache.findByM5", query = "SELECT t FROM Tache t WHERE t.m5 = :m5"),
    @NamedQuery(name = "Tache.findByM6", query = "SELECT t FROM Tache t WHERE t.m6 = :m6"),
    @NamedQuery(name = "Tache.findByM7", query = "SELECT t FROM Tache t WHERE t.m7 = :m7"),
    @NamedQuery(name = "Tache.findByM8", query = "SELECT t FROM Tache t WHERE t.m8 = :m8"),
    @NamedQuery(name = "Tache.findByM9", query = "SELECT t FROM Tache t WHERE t.m9 = :m9"),
    @NamedQuery(name = "Tache.findByM10", query = "SELECT t FROM Tache t WHERE t.m10 = :m10"),
    @NamedQuery(name = "Tache.findByM11", query = "SELECT t FROM Tache t WHERE t.m11 = :m11"),
    @NamedQuery(name = "Tache.findByM12", query = "SELECT t FROM Tache t WHERE t.m12 = :m12"),
    @NamedQuery(name = "Tache.findByPeriodeexecution", query = "SELECT t FROM Tache t WHERE t.periodeexecution = :periodeexecution"),
    @NamedQuery(name = "Tache.findByValider", query = "SELECT t FROM Tache t WHERE t.valider = :valider"),
    @NamedQuery(name = "Tache.findByMontantengage", query = "SELECT t FROM Tache t WHERE t.montantengage = :montantengage"),
    @NamedQuery(name = "Tache.findByMontantliquide", query = "SELECT t FROM Tache t WHERE t.montantliquide = :montantliquide"),
    @NamedQuery(name = "Tache.findByMontantpayeht", query = "SELECT t FROM Tache t WHERE t.montantpayeht = :montantpayeht"),
    @NamedQuery(name = "Tache.findByMontantpayettc", query = "SELECT t FROM Tache t WHERE t.montantpayettc = :montantpayettc"),
    @NamedQuery(name = "Tache.findByAeencours", query = "SELECT t FROM Tache t WHERE t.aeencours = :aeencours"),
    @NamedQuery(name = "Tache.findByCpconsommee", query = "SELECT t FROM Tache t WHERE t.cpconsommee = :cpconsommee")})
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idtache;
    private Integer numordre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double totalmontantaloue;
    @Size(max = 254)
    private String moyensnecessaires;
    @Size(max = 254)
    private String indicateurs2014;
    @Size(max = 600)
    private String nom;
    @Size(max = 800)
    private String resultatattendu;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @Size(max = 254)
    private String indicateurspoursuivis;
    private boolean m1;
    private boolean m2;
    private boolean m3;
    private boolean m4;
    private boolean m5;
    private boolean m6;
    private boolean m7;
    private boolean m8;
    private boolean m9;
    private boolean m10;
    private boolean m11;
    private boolean m12;
    @Size(max = 254)
    private String periodeexecution;
    private Boolean valider;
    private Double montantengage;
    private Double montantliquide;
    private Double montantpayeht;
    private Double montantpayettc;
    private Double aeencours;
    private Double cpconsommee;
    @JoinColumn(name = "idactivite", referencedColumnName = "idactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Activite idactivite;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idbailleurfond", referencedColumnName = "idbailleurfond")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bailleurfond idbailleurfond;
    @JoinColumn(name = "idbudget", referencedColumnName = "idbudget")
    @ManyToOne(fetch = FetchType.LAZY)
    private Budget idbudget;
    @JoinColumn(name = "idnature_t", referencedColumnName = "idnature_t")
    @ManyToOne(fetch = FetchType.LAZY)
    private NatureT idnatureT;
    @JoinColumn(name = "idnaturetache", referencedColumnName = "idnaturetache")
    @ManyToOne(fetch = FetchType.LAZY)
    private Naturetache idnaturetache;
    @JoinColumn(name = "idrisque", referencedColumnName = "idrisque")
    @ManyToOne(fetch = FetchType.LAZY)
    private Risque idrisque;
    @JoinColumn(name = "idtypefinancement", referencedColumnName = "idtypefinancement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typefinancement idtypefinancement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tache", fetch = FetchType.LAZY)
    private Collection<ContratTache> contratTacheCollection;
    @OneToMany(mappedBy = "idtache", fetch = FetchType.LAZY)
    private Collection<Moyens> moyensCollection;
    @JoinColumn(name = "idevaluationstructure", referencedColumnName = "idevaluationstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Evaluationstructure idevaluationstructure;

    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Tache() {
    }

    public Tache(Long idtache) {
        this.idtache = idtache;
    }

    public Long getIdtache() {
        return idtache;
    }

    public void setIdtache(Long idtache) {
        this.idtache = idtache;
    }

    public Integer getNumordre() {
        return numordre;
    }

    public void setNumordre(Integer numordre) {
        this.numordre = numordre;
    }

    public Double getTotalmontantaloue() {
        return totalmontantaloue;
    }

    public void setTotalmontantaloue(Double totalmontantaloue) {
        this.totalmontantaloue = totalmontantaloue;
    }

    public String getMoyensnecessaires() {
        return moyensnecessaires;
    }

    public void setMoyensnecessaires(String moyensnecessaires) {
        this.moyensnecessaires = moyensnecessaires;
    }

    public String getIndicateurs2014() {
        return indicateurs2014;
    }

    public void setIndicateurs2014(String indicateurs2014) {
        this.indicateurs2014 = indicateurs2014;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getResultatattendu() {
        return resultatattendu;
    }

    public void setResultatattendu(String resultatattendu) {
        this.resultatattendu = resultatattendu;
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

    public String getIndicateurspoursuivis() {
        return indicateurspoursuivis;
    }

    public void setIndicateurspoursuivis(String indicateurspoursuivis) {
        this.indicateurspoursuivis = indicateurspoursuivis;
    }

    public Boolean getM1() {
        return m1;
    }

    public void setM1(Boolean m1) {
        this.m1 = m1;
    }

    public Boolean getM2() {
        return m2;
    }

    public void setM2(Boolean m2) {
        this.m2 = m2;
    }

    public Boolean getM3() {
        return m3;
    }

    public void setM3(Boolean m3) {
        this.m3 = m3;
    }

    public Boolean getM4() {
        return m4;
    }

    public void setM4(Boolean m4) {
        this.m4 = m4;
    }

    public Boolean getM5() {
        return m5;
    }

    public void setM5(Boolean m5) {
        this.m5 = m5;
    }

    public Boolean getM6() {
        return m6;
    }

    public void setM6(Boolean m6) {
        this.m6 = m6;
    }

    public Boolean getM7() {
        return m7;
    }

    public void setM7(Boolean m7) {
        this.m7 = m7;
    }

    public Boolean getM8() {
        return m8;
    }

    public void setM8(Boolean m8) {
        this.m8 = m8;
    }

    public Boolean getM9() {
        return m9;
    }

    public void setM9(Boolean m9) {
        this.m9 = m9;
    }

    public Boolean getM10() {
        return m10;
    }

    public void setM10(Boolean m10) {
        this.m10 = m10;
    }

    public Boolean getM11() {
        return m11;
    }

    public void setM11(Boolean m11) {
        this.m11 = m11;
    }

    public Boolean getM12() {
        return m12;
    }

    public void setM12(Boolean m12) {
        this.m12 = m12;
    }

    public String getPeriodeexecution() {
        return periodeexecution;
    }

    public void setPeriodeexecution(String periodeexecution) {
        this.periodeexecution = periodeexecution;
    }

    public Boolean getValider() {
        return valider;
    }

    public void setValider(Boolean valider) {
        this.valider = valider;
    }

    public Double getMontantengage() {
        return montantengage;
    }

    public void setMontantengage(Double montantengage) {
        this.montantengage = montantengage;
    }

    public Double getMontantliquide() {
        return montantliquide;
    }

    public void setMontantliquide(Double montantliquide) {
        this.montantliquide = montantliquide;
    }

    public Double getMontantpayeht() {
        return montantpayeht;
    }

    public void setMontantpayeht(Double montantpayeht) {
        this.montantpayeht = montantpayeht;
    }

    public Double getMontantpayettc() {
        return montantpayettc;
    }

    public void setMontantpayettc(Double montantpayettc) {
        this.montantpayettc = montantpayettc;
    }

    public Double getAeencours() {
        return aeencours;
    }

    public void setAeencours(Double aeencours) {
        this.aeencours = aeencours;
    }

    public Double getCpconsommee() {
        return cpconsommee;
    }

    public void setCpconsommee(Double cpconsommee) {
        this.cpconsommee = cpconsommee;
    }

    public Activite getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Activite idactivite) {
        this.idactivite = idactivite;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Bailleurfond getIdbailleurfond() {
        return idbailleurfond;
    }

    public void setIdbailleurfond(Bailleurfond idbailleurfond) {
        this.idbailleurfond = idbailleurfond;
    }

    public Budget getIdbudget() {
        return idbudget;
    }

    public void setIdbudget(Budget idbudget) {
        this.idbudget = idbudget;
    }

    public NatureT getIdnatureT() {
        return idnatureT;
    }

    public void setIdnatureT(NatureT idnatureT) {
        this.idnatureT = idnatureT;
    }

    public Naturetache getIdnaturetache() {
        return idnaturetache;
    }

    public void setIdnaturetache(Naturetache idnaturetache) {
        this.idnaturetache = idnaturetache;
    }

    public Risque getIdrisque() {
        return idrisque;
    }

    public void setIdrisque(Risque idrisque) {
        this.idrisque = idrisque;
    }

    public Typefinancement getIdtypefinancement() {
        return idtypefinancement;
    }

    public void setIdtypefinancement(Typefinancement idtypefinancement) {
        this.idtypefinancement = idtypefinancement;
    }

    public Evaluationstructure getIdevaluationstructure() {
        return idevaluationstructure;
    }

    public void setIdevaluationstructure(Evaluationstructure idevaluationstructure) {
        this.idevaluationstructure = idevaluationstructure;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @XmlTransient
    public Collection<ContratTache> getContratTacheCollection() {
        return contratTacheCollection;
    }

    public void setContratTacheCollection(Collection<ContratTache> contratTacheCollection) {
        this.contratTacheCollection = contratTacheCollection;
    }

    @XmlTransient
    public Collection<Moyens> getMoyensCollection() {
        return moyensCollection;
    }

    public void setMoyensCollection(Collection<Moyens> moyensCollection) {
        this.moyensCollection = moyensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtache != null ? idtache.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tache)) {
            return false;
        }
        Tache other = (Tache) object;
        if ((this.idtache == null && other.idtache != null) || (this.idtache != null && !this.idtache.equals(other.idtache))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tache[ idtache=" + idtache + " ]";
    }

}
