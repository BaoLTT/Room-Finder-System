package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String reportStatus;
    @Basic
    @Column(name = "solved_date")
    private Date solvedDate;
    @Basic
    @Column(name = "userid")
    private Integer userid;
    @Basic
    @Column(name = "houseid")
    private Integer houseid;


}
