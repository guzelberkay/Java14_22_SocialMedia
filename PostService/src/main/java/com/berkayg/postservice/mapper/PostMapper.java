package com.berkayg.postservice.mapper;


import com.berkayg.postservice.dto.response.PostFindAllResponseDto;
import com.berkayg.postservice.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE= Mappers.getMapper(PostMapper.class);


    PostFindAllResponseDto postToPostFindAllResponseDto(Post post);

}