package com.example.Webhienmau.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    private String eventId;

    private String title;
    private String description;
    private int eventStatus;
    private int eventType;
    private int isUrgent;

    @OneToMany(mappedBy = "event")
    private List<BloodDonationSchedule> schedules;
}
