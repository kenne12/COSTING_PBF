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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Structure.findAll", query = "SELECT s FROM Structure s"),
    @NamedQuery(name = "Structure.findByIdstructure", query = "SELECT s FROM Structure s WHERE s.idstructure = :idstructure"),
    @NamedQuery(name = "Structure.findByNom", query = "SELECT s FROM Structure s WHERE s.nom = :nom"),
    @NamedQuery(name = "Structure.findByArticle", query = "SELECT s FROM Structure s WHERE s.article = :article"),
    @NamedQuery(name = "Structure.findByNomrespo", query = "SELECT s FROM Structure s WHERE s.nomrespo = :nomrespo"),
    @NamedQuery(name = "Structure.findByArretecreation", query = "SELECT s FROM Structure s WHERE s.arretecreation = :arretecreation"),
    @NamedQuery(name = "Structure.findByDesignation", query = "SELECT s FROM Structure s WHERE s.designation = :designation"),
    @NamedQuery(name = "Structure.findByCordonex", query = "SELECT s FROM Structure s WHERE s.cordonex = :cordonex"),
    @NamedQuery(name = "Structure.findByCordoney", query = "SELECT s FROM Structure s WHERE s.cordoney = :cordoney"),
    @NamedQuery(name = "Structure.findByPresentation", query = "SELECT s FROM Structure s WHERE s.presentation = :presentation"),
    @NamedQuery(name = "Structure.findByPap", query = "SELECT s FROM Structure s WHERE s.pap = :pap"),
    @NamedQuery(name = "Structure.findByActif", query = "SELECT s FROM Structure s WHERE s.actif = :actif"),
    @NamedQuery(name = "Structure.findByDateouverture", query = "SELECT s FROM Structure s WHERE s.dateouverture = :dateouverture"),
    @NamedQuery(name = "Structure.findByDateEnregistre", query = "SELECT s FROM Structure s WHERE s.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Structure.findByDerniereModif", query = "SELECT s FROM Structure s WHERE s.derniereModif = :derniereModif"),
    @NamedQuery(name = "Structure.findByVision", query = "SELECT s FROM Structure s WHERE s.vision = :vision"),
    @NamedQuery(name = "Structure.findByAxeintervention", query = "SELECT s FROM Structure s WHERE s.axeintervention = :axeintervention"),
    @NamedQuery(name = "Structure.findByObjectifgeneral", query = "SELECT s FROM Structure s WHERE s.objectifgeneral = :objectifgeneral"),
    @NamedQuery(name = "Structure.findByObjectifspecifique", query = "SELECT s FROM Structure s WHERE s.objectifspecifique = :objectifspecifique"),
    @NamedQuery(name = "Structure.findByEtat", query = "SELECT s FROM Structure s WHERE s.etat = :etat")})
public class Structure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idstructure;
    @Size(max = 254)
    private String nom;
    private String contractant;
    @Column(name = "rep_contractant")
    private String repContractant;
    @Column(name = "titre_rep_contractant")
    private String titrRepContractant;
    @Column(name = "contracte_s_name")
    private String contracteSName;
    @Column(name = "contracte_full_name")
    private String contracteFullName;
    @Column(name = "rep_contracte")
    private String repContracte;
    @Column(name = "titre_contracte")
    private String titre_contracte;
    @Size(max = 254)
    private String article;
    @Size(max = 254)
    private String nomrespo;
    @Size(max = 254)
    private String arretecreation;
    @Size(max = 254)
    private String designation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double cordonex;
    private Double cordoney;
    @Size(max = 254)
    private String presentation;
    private Boolean pap;
    private Boolean actif;
    @Temporal(TemporalType.DATE)
    private Date dateouverture;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @Size(max = 254)
    private String vision;
    @Size(max = 254)
    private String axeintervention;
    @Size(max = 254)
    private String objectifgeneral;
    @Size(max = 254)
    private String objectifspecifique;
    @Size(max = 254)
    private String etat;
    @Column(name = "article_contracte_full_name", length = 4)
    private String articleContracteFullName;
    @Column(name = "article_contractant_full_name", length = 4)
    private String articleContractantFullName;
    @Column(name = "de_la_du_contracte", length = 8)
    private String deLaDuContracte;
    @Column(name = "de_la_du_contractant", length = 8)
    private String deLaDuContractant;
    @Column(name = "contractant_s_name", length = 30)
    private String contractantSName;

    @Column(name = "contractant_represente", length = 30)
    private String contractantRepresente;

    @Column(name = "contracte_represente", length = 30)
    private String contracteRepresente;

    @JoinTable(name = "utilisateurstructure", joinColumns = {
        @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")}, inverseJoinColumns = {
        @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Utilisateur> utilisateurCollection;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private Collection<Contrat> contratCollection;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private Collection<Evaluationstructure> evaluationstructureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "structure", fetch = FetchType.LAZY)
    private Collection<Criterestructure> criterestructureCollection;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private Collection<Plafondbudget> plafondbudgetCollection;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private Collection<Detailsc> detailscCollection;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private Collection<Activite> activiteCollection;
    @JoinColumn(name = "idinstitution", referencedColumnName = "idinstitution")
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution idinstitution;
    @JoinColumn(name = "idpyramide", referencedColumnName = "idpyramide")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pyramide idpyramide;
    @JoinColumn(name = "idstatutstructure", referencedColumnName = "idstatutstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Statutstructure idstatutstructure;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;

    public Structure() {
    }

    public Structure(Long idstructure) {
        this.idstructure = idstructure;
    }

    public Long getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Long idstructure) {
        this.idstructure = idstructure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContractant() {
        return contractant;
    }

    public void setContractant(String contractant) {
        this.contractant = contractant;
    }

    public String getRepContractant() {
        return repContractant;
    }

    public void setRepContractant(String repContractant) {
        this.repContractant = repContractant;
    }

    public String getTitrRepContractant() {
        return titrRepContractant;
    }

    public void setTitrRepContractant(String titrRepContractant) {
        this.titrRepContractant = titrRepContractant;
    }

    public String getContracteSName() {
        return contracteSName;
    }

    public void setContracteSName(String contracteSName) {
        this.contracteSName = contracteSName;
    }

    public String getContracteFullName() {
        return contracteFullName;
    }

    public void setContracteFullName(String contracteFullName) {
        this.contracteFullName = contracteFullName;
    }

    public String getRepContracte() {
        return repContracte;
    }

    public void setRepContracte(String repContracte) {
        this.repContracte = repContracte;
    }

    public String getTitre_contracte() {
        return titre_contracte;
    }

    public void setTitre_contracte(String titre_contracte) {
        this.titre_contracte = titre_contracte;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNomrespo() {
        return nomrespo;
    }

    public void setNomrespo(String nomrespo) {
        this.nomrespo = nomrespo;
    }

    public String getArretecreation() {
        return arretecreation;
    }

    public void setArretecreation(String arretecreation) {
        this.arretecreation = arretecreation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getCordonex() {
        return cordonex;
    }

    public void setCordonex(Double cordonex) {
        this.cordonex = cordonex;
    }

    public Double getCordoney() {
        return cordoney;
    }

    public void setCordoney(Double cordoney) {
        this.cordoney = cordoney;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Boolean getPap() {
        return pap;
    }

    public void setPap(Boolean pap) {
        this.pap = pap;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Date getDateouverture() {
        return dateouverture;
    }

    public void setDateouverture(Date dateouverture) {
        this.dateouverture = dateouverture;
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

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getAxeintervention() {
        return axeintervention;
    }

    public void setAxeintervention(String axeintervention) {
        this.axeintervention = axeintervention;
    }

    public String getObjectifgeneral() {
        return objectifgeneral;
    }

    public void setObjectifgeneral(String objectifgeneral) {
        this.objectifgeneral = objectifgeneral;
    }

    public String getObjectifspecifique() {
        return objectifspecifique;
    }

    public void setObjectifspecifique(String objectifspecifique) {
        this.objectifspecifique = objectifspecifique;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getArticleContracteFullName() {
        return articleContracteFullName;
    }

    public void setArticleContracteFullName(String articleContracteFullName) {
        this.articleContracteFullName = articleContracteFullName;
    }

    public String getArticleContractantFullName() {
        return articleContractantFullName;
    }

    public void setArticleContractantFullName(String articleContractantFullName) {
        this.articleContractantFullName = articleContractantFullName;
    }

    public String getDeLaDuContracte() {
        return deLaDuContracte;
    }

    public void setDeLaDuContracte(String deLaDuContracte) {
        this.deLaDuContracte = deLaDuContracte;
    }

    public String getDeLaDuContractant() {
        return deLaDuContractant;
    }

    public void setDeLaDuContractant(String deLaDuContractant) {
        this.deLaDuContractant = deLaDuContractant;
    }

    public String getContractantSName() {
        return contractantSName;
    }

    public void setContractantSName(String contractantSName) {
        this.contractantSName = contractantSName;
    }

    public String getContractantRepresente() {
        return contractantRepresente;
    }

    public void setContractantRepresente(String contractantRepresente) {
        this.contractantRepresente = contractantRepresente;
    }

    public String getContracteRepresente() {
        return contracteRepresente;
    }

    public void setContracteRepresente(String contracteRepresente) {
        this.contracteRepresente = contracteRepresente;
    }

    @XmlTransient
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    @XmlTransient
    public Collection<Contrat> getContratCollection() {
        return contratCollection;
    }

    public void setContratCollection(Collection<Contrat> contratCollection) {
        this.contratCollection = contratCollection;
    }

    @XmlTransient
    public Collection<Evaluationstructure> getEvaluationstructureCollection() {
        return evaluationstructureCollection;
    }

    public void setEvaluationstructureCollection(Collection<Evaluationstructure> evaluationstructureCollection) {
        this.evaluationstructureCollection = evaluationstructureCollection;
    }

    @XmlTransient
    public Collection<Criterestructure> getCriterestructureCollection() {
        return criterestructureCollection;
    }

    public void setCriterestructureCollection(Collection<Criterestructure> criterestructureCollection) {
        this.criterestructureCollection = criterestructureCollection;
    }

    @XmlTransient
    public Collection<Plafondbudget> getPlafondbudgetCollection() {
        return plafondbudgetCollection;
    }

    public void setPlafondbudgetCollection(Collection<Plafondbudget> plafondbudgetCollection) {
        this.plafondbudgetCollection = plafondbudgetCollection;
    }

    @XmlTransient
    public Collection<Detailsc> getDetailscCollection() {
        return detailscCollection;
    }

    public void setDetailscCollection(Collection<Detailsc> detailscCollection) {
        this.detailscCollection = detailscCollection;
    }

    @XmlTransient
    public Collection<Activite> getActiviteCollection() {
        return activiteCollection;
    }

    public void setActiviteCollection(Collection<Activite> activiteCollection) {
        this.activiteCollection = activiteCollection;
    }

    public Institution getIdinstitution() {
        return idinstitution;
    }

    public void setIdinstitution(Institution idinstitution) {
        this.idinstitution = idinstitution;
    }

    public Pyramide getIdpyramide() {
        return idpyramide;
    }

    public void setIdpyramide(Pyramide idpyramide) {
        this.idpyramide = idpyramide;
    }

    public Statutstructure getIdstatutstructure() {
        return idstatutstructure;
    }

    public void setIdstatutstructure(Statutstructure idstatutstructure) {
        this.idstatutstructure = idstatutstructure;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstructure != null ? idstructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Structure)) {
            return false;
        }
        Structure other = (Structure) object;
        if ((this.idstructure == null && other.idstructure != null) || (this.idstructure != null && !this.idstructure.equals(other.idstructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Structure[ idstructure=" + idstructure + " ]";
    }

}
