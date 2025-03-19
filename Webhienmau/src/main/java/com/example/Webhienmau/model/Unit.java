package com.example.Webhienmau.model;

import com.example.Webhienmau.model.BloodDonationSchedule;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unit {
    @Id
    @Column(name = "unit_id", unique = true, nullable = false)
    private String unitId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String donateAddress;

    @OneToMany(mappedBy = "unit")
    private List<BloodDonationSchedule> schedules;
}
