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
@Table(name = "tb_m_sylabus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sylabus.findAll", query = "SELECT s FROM Sylabus s")
    , @NamedQuery(name = "Sylabus.findById", query = "SELECT s FROM Sylabus s WHERE s.id = :id")
    , @NamedQuery(name = "Sylabus.findByPercentage", query = "SELECT s FROM Sylabus s WHERE s.percentage = :percentage")})
public class Sylabus implements Serializable {

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
    @JoinColumn(name = "lesson", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lesson lesson;
    @JoinColumn(name = "class", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Classes class1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sylabus", fetch = FetchType.LAZY)
    private List<LessonCriteria> lessonCriteriaList;

    public Sylabus() {
    }

    public Sylabus(String id) {
        this.id = id;
    }

    public Sylabus(String id, float percentage) {
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Classes getClass1() {
        return class1;
    }

    public void setClass1(Classes class1) {
        this.class1 = class1;
    }

    @XmlTransient
    public List<LessonCriteria> getLessonCriteriaList() {
        return lessonCriteriaList;
    }

    public void setLessonCriteriaList(List<LessonCriteria> lessonCriteriaList) {
        this.lessonCriteriaList = lessonCriteriaList;
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
        if (!(object instanceof Sylabus)) {
            return false;
        }
        Sylabus other = (Sylabus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bootcamp.cobaspringapplication.entities.Sylabus[ id=" + id + " ]";
    }
    
}
