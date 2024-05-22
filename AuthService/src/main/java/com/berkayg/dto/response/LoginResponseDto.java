package com.berkayg.dto.response;

import com.berkayg.entity.ERole;
import com.berkayg.entity.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginResponseDto {
    Long id;
    String username;
    String email;
    ERole role;
    EStatus status;
}