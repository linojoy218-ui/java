package com.aitrich.JobPortalSystem.model.jobSeeker;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Profile_Creation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobseeker_id;
    private String name;
    @NotNull(message = "Data of Birth is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dob;
    private String gender;
    private String qualification;
    private String certification;
    private int String;
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
    }

}
