/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluationstructure.findAll", query = "SELECT e FROM Evaluationstructure e"),
    @NamedQuery(name = "Evaluationstructure.findByIdevaluationstructure", query = "SELECT e FROM Evaluationstructure e WHERE e.idevaluationstructure = :idevaluationstructure"),
    @NamedQuery(name = "Evaluationstructure.findByScorePeriodePrec", query = "SELECT e FROM Evaluationstructure e WHERE e.scorePeriodePrec = :scorePeriodePrec"),
    @NamedQuery(name = "Evaluationstructure.findByScorePeriodeActuelPrevi", query = "SELECT e FROM Evaluationstructure e WHERE e.scorePeriodeActuelPrevi = :scorePeriodeActuelPrevi"),
    @NamedQuery(name = "Evaluationstructure.findByScorePeriodeActuelReal", query = "SELECT e FROM Evaluationstructure e WHERE e.scorePeriodeActuelReal = :scorePeriodeActuelReal"),
    @NamedQuery(name = "Evaluationstructure.findByApplicable", query = "SELECT e FROM Evaluationstructure e WHERE e.applicable = :applicable")})
public class Evaluationstructure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idevaluationstructure;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "score_periode_prec")
    private Double scorePeriodePrec;
    @Column(name = "score_periode_actuel_previ")
    private Double scorePeriodeActuelPrevi;
    @Column(name = "score_periode_actuel_real")
    private Double scorePeriodeActuelReal;
    private Boolean applicable;
    @Size(max = 2000)
    private String probleme;
    @JoinColumn(name = "idbudget", referencedColumnName = "idbudget")
    @ManyToOne(fetch = FetchType.LAZY)
    private Budget idbudget;
    @JoinColumn(name = "iddetailsc", referencedColumnName = "iddetailsc")
    @ManyToOne(fetch = FetchType.LAZY)
    private Detailsc iddetailsc;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Evaluationstructure() {
    }

    public Evaluationstructure(Long idevaluationstructure) {
        this.idevaluationstructure = idevaluationstructure;
    }

    public Long getIdevaluationstructure() {
        return idevaluationstructure;
    }

    public void setIdevaluationstructure(Long idevaluationstructure) {
        this.idevaluationstructure = idevaluationstructure;
    }

    public Double getScorePeriodePrec() {
        return scorePeriodePrec;
    }

    public void setScorePeriodePrec(Double scorePeriodePrec) {
        this.scorePeriodePrec = scorePeriodePrec;
    }

    public Double getScorePeriodeActuelPrevi() {
        return scorePeriodeActuelPrevi;
    }

    public void setScorePeriodeActuelPrevi(Double scorePeriodeActuelPrevi) {
        this.scorePeriodeActuelPrevi = scorePeriodeActuelPrevi;
    }

    public Double getScorePeriodeActuelReal() {
        return scorePeriodeActuelReal;
    }

    public void setScorePeriodeActuelReal(Double scorePeriodeActuelReal) {
        this.scorePeriodeActuelReal = scorePeriodeActuelReal;
    }

    public Boolean getApplicable() {
        return applicable;
    }

    public void setApplicable(Boolean applicable) {
        this.applicable = applicable;
    }

    public Budget getIdbudget() {
        return idbudget;
    }

    public void setIdbudget(Budget idbudget) {
        this.idbudget = idbudget;
    }

    public Detailsc getIddetailsc() {
        return iddetailsc;
    }

    public void setIddetailsc(Detailsc iddetailsc) {
        this.iddetailsc = iddetailsc;
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

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevaluationstructure != null ? idevaluationstructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluationstructure)) {
            return false;
        }
        Evaluationstructure other = (Evaluationstructure) object;
        if ((this.idevaluationstructure == null && other.idevaluationstructure != null) || (this.idevaluationstructure != null && !this.idevaluationstructure.equals(other.idevaluationstructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evaluationstructure[ idevaluationstructure=" + idevaluationstructure + " ]";
    }

}
