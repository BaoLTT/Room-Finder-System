package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "report", schema = "room_finding_system", catalog = "")
public class ReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reportid")
    private int reportid;
    @Basic
    @Column(name = "report_description")
    private String reportDescription;
    @Basic
    @Column(name = "created_date")
    private Date createdDate;
    @Basic
    @Column(name = "report_status")
    private Integer reportStatusid;
    @Basic
    @Column(name = "solved_date")
    private Date solvedDate;
    @Basic
    @Column(name = "userid")
    private Integer userid;

    public int getReportid() {
        return reportid;
    }

    public void setReportid(int reportid) {
        this.reportid = reportid;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getReportStatusid() {
        return reportStatusid;
    }

    public void setReportStatusid(Integer reportStatusid) {
        this.reportStatusid = reportStatusid;
    }

    public Date getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(Date solvedDate) {
        this.solvedDate = solvedDate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return reportid == that.reportid && Objects.equals(reportDescription, that.reportDescription) && Objects.equals(createdDate, that.createdDate) && Objects.equals(reportStatusid, that.reportStatusid) && Objects.equals(solvedDate, that.solvedDate) && Objects.equals(userid, that.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportid, reportDescription, createdDate, reportStatusid, solvedDate, userid);
    }
}
