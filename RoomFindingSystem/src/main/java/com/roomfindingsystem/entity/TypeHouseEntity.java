package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "TypeHouse", schema = "RoomFindingSystem", catalog = "")
public class TypeHouseEntity {
    @Id
    @Basic
    @Column(name = "TypeID")
    private int typeId;
    @Basic
    @Column(name = "TypeName")
    private String typeName;
    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeHouseEntity that = (TypeHouseEntity) o;
        return typeId == that.typeId && Objects.equals(typeName, that.typeName) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName, createdDate);
    }
}
