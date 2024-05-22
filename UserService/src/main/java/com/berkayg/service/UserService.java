package com.berkayg.service;


import com.berkayg.dto.request.UserSaveRequestDto;
import com.berkayg.dto.request.UserUpdateRequestDto;
import com.berkayg.entity.EStatus;
import com.berkayg.entity.UserProfile;
import com.berkayg.exception.ErrorType;
import com.berkayg.exception.UserServiceException;
import com.berkayg.manager.AuthManager;
import com.berkayg.mapper.UserMapper;
import com.berkayg.repository.UserRepository;
import com.berkayg.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthManager authManager;

    public Boolean save(UserSaveRequestDto dto) {
        UserProfile save = userRepository.save(UserMapper.INSTANCE.toUserProfile(dto));
        if (save == null)
            throw new UserServiceException(ErrorType.INTERNAL_SERVER_ERROR);
        return true;
    }

    public void updateStatus(Long authId) {
        UserProfile foundUserProfile = userRepository.findByAuthId(authId)
                .orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
        foundUserProfile.setStatus(EStatus.ACTIVE);
        UserProfile savedUserProfile = userRepository.save(foundUserProfile);
        if (!savedUserProfile.getStatus().equals(EStatus.ACTIVE)) {
            throw new UserServiceException(ErrorType.USERSERVICE_UPDATE_STATUS_FAILED);
        }
    }

    public void updateUserProfile(UserUpdateRequestDto dto) {
        Long authId =
                jwtTokenManager.getIdFromToken(dto.getToken())
                        .orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));

        UserProfile userProfile = userRepository.findByAuthId(authId)
                .orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
        if (dto.getEmail() != null) {
            userProfile.setEmail(dto.getEmail());
            //authda da değişmeli.
            authManager.updatemail(authId,dto);
        }
        if (dto.getPhone() != null) {
            userProfile.setPhone(dto.getPhone());
        }
        if (dto.getPhoto() != null) {
            userProfile.setPhoto(dto.getPhoto());
        }
        if (dto.getAddress() != null) {
            userProfile.setAddress(dto.getAddress());
        }
        if (dto.getAbout() != null) {
            userProfile.setAbout(dto.getAbout());
        }
        userRepository.save(userProfile);
    }


    public  void delete(Long authId) {
        UserProfile userProfile =userRepository.findByAuthId(authId)
                .orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
        userProfile.setStatus(EStatus.DELETED);
        userRepository.save(userProfile);
    }
    public String getUserByAuthId(Long authId) {
        UserProfile userProfile = userRepository.findByAuthId(authId)
                .orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
        return userProfile.getId();
    }
}