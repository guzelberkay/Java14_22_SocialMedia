package com.berkayg.mapper;

import com.berkayg.dto.request.RegisterRequestDto;
import com.berkayg.dto.request.UserSaveRequestDto;
import com.berkayg.dto.response.LoginResponseDto;
import com.berkayg.dto.response.RegisterResponseDto;
import com.berkayg.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper INSTANCE= Mappers.getMapper(AuthMapper.class);

    Auth toAuth(RegisterRequestDto dto);

    RegisterResponseDto authToDto(Auth auth);

    LoginResponseDto toLoginResponseDto(Auth auth);
    @Mapping(source = "id",target = "authId")
    UserSaveRequestDto toUserSaveRequestDto(Auth auth);
}