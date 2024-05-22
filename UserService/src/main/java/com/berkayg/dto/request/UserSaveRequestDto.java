package com.berkayg.dto.request;

import com.berkayg.entity.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserSaveRequestDto {
    Long authId;
    String username;
    String email;
}