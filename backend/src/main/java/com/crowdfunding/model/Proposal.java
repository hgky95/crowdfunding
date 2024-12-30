package com.crowdfunding.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "proposals")
public class Proposal {
    @Id
    private BigInteger id;
    
    private String title;
    private String contentCID;
    private String planCID;
    private String student;
    private Integer status;
    
    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<Milestone> milestones;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 