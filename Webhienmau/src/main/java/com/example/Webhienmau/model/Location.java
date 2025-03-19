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
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    private String locationId;

    private String donatePlace;
    private String address;
    private String city;
    private String district;
    private String ward;

    @OneToMany(mappedBy = "location")
    private List<BloodDonationSchedule> schedules;
}
