package com.berkayg.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder //üst sınıfa bu anotasyonlar eklenmelidir.
@MappedSuperclass //üst sınıfa bu anotasyonlar eklenmelidir.
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class BaseEntity {

    @CreationTimestamp
    LocalDateTime createAt;
    @UpdateTimestamp
    LocalDateTime updateAt;
    @Builder.Default
    Boolean state=true;
}
