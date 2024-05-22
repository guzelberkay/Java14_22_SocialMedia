package com.berkayg.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_auth")
public class Auth extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    String password;
    String email;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    ERole role= ERole.USER;
    String activationCode;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    EStatus status= EStatus.PENDING;



}
