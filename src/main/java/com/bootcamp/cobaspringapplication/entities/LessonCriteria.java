/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arman
 */
@Entity
@Table(name = "tb_m_lesson_criteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LessonCriteria.findAll", query = "SELECT l FROM LessonCriteria l")
    , @NamedQuery(name = "LessonCriteria.findById", query = "SELECT l FROM LessonCriteria l WHERE l.id = :id")
    , @NamedQuery(name = "LessonCriteria.findByPercentage", query = "SELECT l FROM LessonCriteria l WHERE l.percentage = :percentage")})
public class LessonCriteria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "percentage")
    private float percentage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonCriteria", fetch = FetchType.LAZY)
    private List<AssessmentDetail> assessmentDetailList;
    @JoinColumn(name = "sylabus", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sylabus sylabus;
    @JoinColumn(name = "criteria", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Criteria criteria;

    public LessonCriteria() {
    }

    public LessonCriteria(String id) {
        this.id = id;
    }

    public LessonCriteria(String id, float percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @XmlTransient
    public List<AssessmentDetail> getAssessmentDetailList() {
        return assessmentDetailList;
    }

    public void setAssessmentDetailList(List<AssessmentDetail> assessmentDetailList) {
        this.assessmentDetailList = assessmentDetailList;
    }

    public Sylabus getSylabus() {
        return sylabus;
    }

    public void setSylabus(Sylabus sylabus) {
        this.sylabus = sylabus;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
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
        if (!(object instanceof LessonCriteria)) {
            return false;
        }
        LessonCriteria other = (LessonCriteria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bootcamp.cobaspringapplication.entities.LessonCriteria[ id=" + id + " ]";
    }
    
}
