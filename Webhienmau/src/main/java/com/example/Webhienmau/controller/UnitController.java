package com.example.Webhienmau.controller;


import com.example.Webhienmau.dto.ApiResponse;
import com.example.Webhienmau.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/units")
public class UnitController {
    @Autowired
    private UnitService unitService;

    @PostMapping("/all")
    public ApiResponse getAllDonationUnits() {
        return unitService.fetchAndSaveDonationUnits();
    }
}
