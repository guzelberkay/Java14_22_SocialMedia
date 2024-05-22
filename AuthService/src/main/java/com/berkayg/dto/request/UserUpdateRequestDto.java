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
    String token;
    String email;
    String photo;
    String phone;
    String address;
    String about;
}
