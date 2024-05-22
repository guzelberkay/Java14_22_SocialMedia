package com.berkayg.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserUpdateRequestDto {
    private String token;
    private String email;
    private String photo;
    private String phone;
    private String address;
    private String about;
}
