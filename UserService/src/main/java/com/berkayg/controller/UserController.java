package com.berkayg.controller;

import com.berkayg.dto.request.UserSaveRequestDto;

import com.berkayg.dto.request.UserUpdateRequestDto;
import com.berkayg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.berkayg.constant.EndPoints.*;


@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserSaveRequestDto dto) {
        return ResponseEntity.ok(userService.save(dto));
    }
    @PutMapping("/updatestatus/{authId}")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long authId) {
        userService.updateStatus(authId);
        return ResponseEntity.ok("User status updated");
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateRequestDto dto) {
        userService.updateUserProfile(dto);
        return ResponseEntity.ok("User updated");
    }


}