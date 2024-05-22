package com.berkayg.postservice.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostFindAllResponseDto {
    String id;
    String userId;
    String content;
    String title;
    String photo;
    LocalDateTime createAt;
}