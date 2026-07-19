package com.aitrich.JobPortalSystem.model.jobSeeker;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Search_Job {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker_id;
    private String keyword;
    private String location;
    private String category;
    private LocalDateTime searched_at;
    @PrePersist
    public void prePersist() {
        this.searched_at = LocalDateTime.now();
    }


}
