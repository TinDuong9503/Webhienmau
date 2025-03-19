package com.example.Webhienmau.config.mapper;

import com.example.Webhienmau.dto.UnitDTO;
import com.example.Webhienmau.dto.UserDTO;
import com.example.Webhienmau.model.Unit;
import com.example.Webhienmau.model.User;

import javax.xml.stream.events.DTD;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {


//    public static EventDTO mapEventEntityToDTO(Event event) {
//        return EventDTO.builder()
//                .eventId(event.getEventId())
//                .donatePlace(event.getDonatePlace())
//                .donateAddress(event.getDonateAddress())
//                .city(event.getCity())
//                .title(event.getTitle())
//                .description(event.getDescription())
//                .isUrgent(event.getIsUrgent())
//                .isFixTour(event.getIsFixTour())
//                .isCompanyRequired(event.getIsCompanyRequired())
//                .tourType(event.getTourType())
//                .eventType(event.getEventType())
//                .donateStartTime(event.getDonateStartTime())
//                .donateEndTime(event.getDonateEndTime())
//                .build();
//    }

//    public static List<EventDTO> mapEventListEntityToDTO(List<Event> events) {
//        return events.stream().map(Utils::mapEventEntityToDTO).collect(Collectors.toList());
//    }


    // Chuyển từ Unit sang UnitDTO
    public static UnitDTO mapDonationUnitEntityToDTO(Unit unit) {
        return new UnitDTO(
                unit.getUnitId(),       // Lấy mã đơn vị
                unit.getName(),         // Lấy tên đơn vị hiến máu
                unit.getDonateAddress() // Lấy địa chỉ đơn vị
        );
    }
    public static UserDTO mapUserEntityToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .cccd(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .fullName(user.getUserProfile().getFullName())
                .role(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.joining(", "))) // Chuyển danh sách Role thành chuỗi
                .build();
    }



    //Chuyển danh sach unit sang dto
    public static List<UnitDTO> mapDonationUnitListEntityToDTO(List<Unit> units) {
        return units.stream()
                .map(Utils::mapDonationUnitEntityToDTO)
                .collect(Collectors.toList());
    }
}
