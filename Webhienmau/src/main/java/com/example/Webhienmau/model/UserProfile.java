package com.example.Webhienmau.model;

import com.example.Webhienmau.model.Enum.BloodType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private String fullName;

    private String job;
    private int gender; // 0 = Nữ, 1 = Nam, 2 = Khác
    private String address;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    private int donateTimes;

    public UserProfile(String fullName) {
        this.fullName = fullName;
    }
}
