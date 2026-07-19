package com.aitrich.JobPortalSystem.model.platformAdmin;


import com.aitrich.JobPortalSystem.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PlatformAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_Id;
    private String name;
    @Email(message = "Invalid email format")
    private String email;
}
