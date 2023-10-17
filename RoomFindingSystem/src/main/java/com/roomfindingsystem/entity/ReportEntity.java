package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "report", schema = "room_finding_system", catalog = "")
public class ReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ReportID")
    private int reportId;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Basic
    @Column(name = "Solved_Date")
    private LocalDate solvedDate;

    public LocalDate getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(LocalDate solvedDate) {
        this.solvedDate = solvedDate;
    }

    @Basic
    @Column(name = "Created_Date")
    private LocalDate createdDate;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "Report_StatusID")
    private Integer reportStatusId;

    public Integer getReportStatusId() {
        return reportStatusId;
    }

    public void setReportStatusId(Integer reportStatusId) {
        this.reportStatusId = reportStatusId;
    }

    @Basic
    @Column(name = "UserID")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return reportId == that.reportId && userId == that.userId && Objects.equals(solvedDate, that.solvedDate) && Objects.equals(createdDate, that.createdDate) && Objects.equals(reportStatusId, that.reportStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, solvedDate, createdDate, reportStatusId, userId);
    }
}
