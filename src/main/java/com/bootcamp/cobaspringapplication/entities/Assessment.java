/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arman
 */
@Entity
@Table(name = "tb_tr_assessment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assessment.findAll", query = "SELECT a FROM Assessment a")
    , @NamedQuery(name = "Assessment.findById", query = "SELECT a FROM Assessment a WHERE a.id = :id")
    , @NamedQuery(name = "Assessment.findByDate", query = "SELECT a FROM Assessment a WHERE a.date = :date")
    , @NamedQuery(name = "Assessment.findByScore", query = "SELECT a FROM Assessment a WHERE a.score = :score")
    , @NamedQuery(name = "Assessment.findBySylabus", query = "SELECT a FROM Assessment a WHERE a.sylabus = :sylabus")})
public class Assessment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private float score;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "sylabus")
    private String sylabus;
    @JoinColumn(name = "participant", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Participant participant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assessment", fetch = FetchType.LAZY)
    private List<AssessmentDetail> assessmentDetailList;

    public Assessment() {
    }

    public Assessment(String id) {
        this.id = id;
    }

    public Assessment(String id, Date date, float score, String sylabus) {
        this.id = id;
        this.date = date;
        this.score = score;
        this.sylabus = sylabus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getSylabus() {
        return sylabus;
    }

    public void setSylabus(String sylabus) {
        this.sylabus = sylabus;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @XmlTransient
    public List<AssessmentDetail> getAssessmentDetailList() {
        return assessmentDetailList;
    }

    public void setAssessmentDetailList(List<AssessmentDetail> assessmentDetailList) {
        this.assessmentDetailList = assessmentDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assessment)) {
            return false;
        }
        Assessment other = (Assessment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bootcamp.cobaspringapplication.entities.Assessment[ id=" + id + " ]";
    }
    
}
