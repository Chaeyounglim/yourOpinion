package com.example.youropinion.entity;

import com.example.youropinion.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // 사용자 아이디

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false, unique = true)
    private String nickname; //사용자 이름

    @Column(nullable = false)
    private String introduce; // 사용자 자기소개

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @ElementCollection
    @CollectionTable(name = "previous_passwords", joinColumns = @JoinColumn(name = "users_id"))
    @Column(name = "password")
    private List<String> previousPasswords = new ArrayList<>();

    public User(SignupRequestDto signupRequestDto, String password, UserRoleEnum role) {
        this.username = signupRequestDto.getUsername();
        this.nickname = signupRequestDto.getNickname();
        this.password = password;
        this.email = signupRequestDto.getEmail();
        this.introduce = signupRequestDto.getIntroduce();
        this.role = role;
    }
}
