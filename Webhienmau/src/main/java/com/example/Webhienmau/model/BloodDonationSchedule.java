package com.example.Webhienmau.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blood_donation_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodDonationSchedule {
    @Id
    private String donateId;

    private String donatePlace;

    private LocalDate donateDate;
    private String donateTimeStart;
    private String donateTimeEnd;

    @OneToMany(mappedBy = "bloodDonationSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationTimeSlot> donateAcceptTimes = new ArrayList<>();

    private int goalBloodBag;
    private int minBloodBag;
    private int maxBloodBag;
    private int currentReg;
    private String donateAddress;
    private int status;
    private String ref;


    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    private int isBloodNeed;
    private int bloodA, bloodB, bloodAB, bloodO;
    private int negativeBloodA, negativeBloodB, negativeBloodAB, negativeBloodO;
    private int regBloodA, regBloodB, regBloodAB, regBloodO;
    private int regNegativeBloodA, regNegativeBloodB, regNegativeBloodAB, regNegativeBloodO;

    private int position;
    private int eventStatus;
    private int tourType;
    private int eventType;
    private int isUrgent;
    private int isFixTour;
    private int goalBloodBagType;
    private int additionalBloodBag;
    private int additionalCurrentReg;
    private int isCompanyRequired;
}
