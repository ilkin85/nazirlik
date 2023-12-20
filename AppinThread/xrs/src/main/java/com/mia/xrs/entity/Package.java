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
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer uniqueId;

    private Integer packageNo;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean status;

    private Date sentDate;

    private Date receiveDate;

    private String senderSignature;

    private String receiverSignature;

    private String note;

    private Integer letterCount;

    @OneToMany(mappedBy = "aPackage")
    private List<Letter> letters;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

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
