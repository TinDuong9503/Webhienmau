package com.example.Webhienmau.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UnitDTO {
    private String unitCode; // "CTD.Q4"
    private String name; // "Hội Chữ Thập Đỏ Quận 4"
    private String donateAddress; // "18 Đoàn Như Hài, Quận 4, TP.HCM"
//    private String donatePlaceEN; // "Red Cross Society Districts 4"

    public UnitDTO(String unitCode, String name, String donateAddress) {
        this.unitCode = unitCode;
        this.name = name;
        this.donateAddress = donateAddress;
    }
}
