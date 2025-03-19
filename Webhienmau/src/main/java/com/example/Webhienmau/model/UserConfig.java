package com.example.Webhienmau.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_config")
public class UserConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private int notiSMS;   // 0 = Tắt thông báo SMS, 1 = Bật
    private int notiEmail; // 0 = Tắt email, 1 = Bật
    private int notiApp;   // 0 = Tắt, 999 = Bật (giá trị lớn có thể là chế độ ưu tiên)

    public UserConfig(int i, int i1, int i2) {
        this.notiSMS = i;
        this.notiEmail = i1;
        this.notiApp = i2;
    }
}
