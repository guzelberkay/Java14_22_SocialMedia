package com.berkayg.manager;

import com.berkayg.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.berkayg.constant.EndPoints.SAVE;


@FeignClient(url = "http://localhost:9091/api/v1/user", name = "userManager", dismiss404 = true)
public interface UserManager {
    @PostMapping(SAVE)
    ResponseEntity<Boolean> save(@RequestBody UserSaveRequestDto dto);

    @PutMapping("/updatestatus/{authId}")
    ResponseEntity<String> updateUserStatus(@PathVariable Long authId);


    @DeleteMapping("/delete/{authId}")
    ResponseEntity<String> deleteUser(@PathVariable Long authId);
}