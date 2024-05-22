package com.berkayg.postservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostUpdateRequestDto {
    String token;
    String postId;
    String content;
    String title;
    String photo;
}