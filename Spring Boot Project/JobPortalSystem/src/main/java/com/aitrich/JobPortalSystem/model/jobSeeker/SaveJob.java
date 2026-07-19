package com.aitrich.JobPortalSystem.model.jobSeeker;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class SaveJob {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker_id;
    private String jobPosting_id;
    private LocalDateTime created_at;
    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
    }
}
