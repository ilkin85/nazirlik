
package com.mia.xrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String routeNo;

    private Date returnDate;

    private Integer uniqueId;

    @OneToOne
    @JoinColumn(name = "letter_id")
    private Letter letter;

    private String rejectReason;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name="returner_id")
    private User returner;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    private User receiver;

    private String returnerSignature;

    private String receiverSignature;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
