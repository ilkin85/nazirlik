package com.mia.xrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer uniqueId;

    private Integer letterNo;

    private String importanceDegree;

    private Integer envelope;

    private Integer parcel;

    private Date date;

    private String note;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "a_package_id")
    private Package aPackage;

    @ManyToOne
    @JoinColumn(name = "from_department_id")
    private Department fromDepartment;

    @ManyToOne
    @JoinColumn(name = "to_department_id")
    private Department toDepartment;

    @OneToOne(mappedBy = "letter")
    private Reject reject;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    @PrePersist
    public void prePersist() {
        setCreatedBy(getAuthenticatedUser());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedBy(getAuthenticatedUser());
    }

    private User getAuthenticatedUser() {

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
