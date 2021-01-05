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
    @NamedQuery(name = "Institution.findAll", query = "SELECT i FROM Institution i"),
    @NamedQuery(name = "Institution.findByIdinstitution", query = "SELECT i FROM Institution i WHERE i.idinstitution = :idinstitution"),
    @NamedQuery(name = "Institution.findByNom", query = "SELECT i FROM Institution i WHERE i.nom = :nom"),
    @NamedQuery(name = "Institution.findByCode", query = "SELECT i FROM Institution i WHERE i.code = :code"),
    @NamedQuery(name = "Institution.findByChoixstrategique", query = "SELECT i FROM Institution i WHERE i.choixstrategique = :choixstrategique"),
    @NamedQuery(name = "Institution.findByEtat", query = "SELECT i FROM Institution i WHERE i.etat = :etat"),
    @NamedQuery(name = "Institution.findByDateEnregistre", query = "SELECT i FROM Institution i WHERE i.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Institution.findByDerniereModif", query = "SELECT i FROM Institution i WHERE i.derniereModif = :derniereModif"),
    @NamedQuery(name = "Institution.findBySigle", query = "SELECT i FROM Institution i WHERE i.sigle = :sigle"),
    @NamedQuery(name = "Institution.findByChapitre", query = "SELECT i FROM Institution i WHERE i.chapitre = :chapitre"),
    @NamedQuery(name = "Institution.findByPhotoInstitutionMere", query = "SELECT i FROM Institution i WHERE i.photoInstitutionMere = :photoInstitutionMere"),
    @NamedQuery(name = "Institution.findByPhoto", query = "SELECT i FROM Institution i WHERE i.photo = :photo"),
    @NamedQuery(name = "Institution.findByNomEn", query = "SELECT i FROM Institution i WHERE i.nomEn = :nomEn")})
public class Institution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idinstitution;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String choixstrategique;
    @Size(max = 254)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @Size(max = 254)
    private String sigle;
    @Size(max = 254)
    private String chapitre;
    @Size(max = 254)
    @Column(name = "photo_institution_mere")
    private String photoInstitutionMere;
    @Size(max = 254)
    private String photo;
    @Size(max = 254)
    @Column(name = "nom_en")
    private String nomEn;
    @JoinColumn(name = "idsoussecteur", referencedColumnName = "idsoussecteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Soussecteur idsoussecteur;
    @OneToMany(mappedBy = "idinstitution", fetch = FetchType.LAZY)
    private Collection<Programme> programmeCollection;
    @OneToMany(mappedBy = "idinstitution", fetch = FetchType.LAZY)
    private Collection<Structure> structureCollection;

    public Institution() {
    }

    public Institution(Long idinstitution) {
        this.idinstitution = idinstitution;
    }

    public Long getIdinstitution() {
        return idinstitution;
    }

    public void setIdinstitution(Long idinstitution) {
        this.idinstitution = idinstitution;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChoixstrategique() {
        return choixstrategique;
    }

    public void setChoixstrategique(String choixstrategique) {
        this.choixstrategique = choixstrategique;
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

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getChapitre() {
        return chapitre;
    }

    public void setChapitre(String chapitre) {
        this.chapitre = chapitre;
    }

    public String getPhotoInstitutionMere() {
        return photoInstitutionMere;
    }

    public void setPhotoInstitutionMere(String photoInstitutionMere) {
        this.photoInstitutionMere = photoInstitutionMere;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public Soussecteur getIdsoussecteur() {
        return idsoussecteur;
    }

    public void setIdsoussecteur(Soussecteur idsoussecteur) {
        this.idsoussecteur = idsoussecteur;
    }

    @XmlTransient
    public Collection<Programme> getProgrammeCollection() {
        return programmeCollection;
    }

    public void setProgrammeCollection(Collection<Programme> programmeCollection) {
        this.programmeCollection = programmeCollection;
    }

    @XmlTransient
    public Collection<Structure> getStructureCollection() {
        return structureCollection;
    }

    public void setStructureCollection(Collection<Structure> structureCollection) {
        this.structureCollection = structureCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinstitution != null ? idinstitution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institution)) {
            return false;
        }
        Institution other = (Institution) object;
        if ((this.idinstitution == null && other.idinstitution != null) || (this.idinstitution != null && !this.idinstitution.equals(other.idinstitution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Institution[ idinstitution=" + idinstitution + " ]";
    }
    
}
