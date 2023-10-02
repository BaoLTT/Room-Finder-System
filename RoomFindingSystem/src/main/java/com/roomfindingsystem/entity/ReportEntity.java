package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Report", schema = "RoomFindingSystem", catalog = "")
public class ReportEntity {
    @Id
    @Basic
    @Column(name = "ReportID")
    private int reportId;
    @Basic
    @Column(name = "SolvedDate")
    private Timestamp solvedDate;
    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
    @Basic
    @Column(name = "ReportStatusID")
    private Integer reportStatusId;
    @Basic
    @Column(name = "User_UserID")
    private int userUserId;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Timestamp getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(Timestamp solvedDate) {
        this.solvedDate = solvedDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getReportStatusId() {
        return reportStatusId;
    }

    public void setReportStatusId(Integer reportStatusId) {
        this.reportStatusId = reportStatusId;
    }

    public int getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return reportId == that.reportId && userUserId == that.userUserId && Objects.equals(solvedDate, that.solvedDate) && Objects.equals(createdDate, that.createdDate) && Objects.equals(reportStatusId, that.reportStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, solvedDate, createdDate, reportStatusId, userUserId);
    }
}
