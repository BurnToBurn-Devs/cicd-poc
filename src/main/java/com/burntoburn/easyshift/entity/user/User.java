package com.burntoburn.easyshift.entity.user;

import com.burntoburn.easyshift.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "users") // user는 일부 DB에서 예약어이므로 users로 사용
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자 (protected)
@AllArgsConstructor // 모든 필드를 포함한 생성자 자동 생성
@Builder // Lombok Builder 적용
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // id는 Builder에서 설정 불가
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // WORKER, ADMINISTRATOR

    private String avatarUrl;

    // 이름 변경하는 메서드
    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }
}
