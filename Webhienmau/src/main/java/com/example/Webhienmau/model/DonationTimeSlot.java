package com.example.Webhienmau.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "donation_time_slot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationTimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donate_id", nullable = false)
    private BloodDonationSchedule bloodDonationSchedule;

    private int donateAcceptTime;
    private int donateStopTime;
    private int maxLimitDonate;
    private int currentReg;
}
