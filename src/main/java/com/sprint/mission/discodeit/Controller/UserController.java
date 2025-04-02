package com.sprint.mission.discodeit.Controller;

import com.sprint.mission.discodeit.dto.data.UserDto;
import com.sprint.mission.discodeit.dto.request.BinaryContentCreateRequest;
import com.sprint.mission.discodeit.dto.request.UserCreateRequest;
import com.sprint.mission.discodeit.dto.request.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.dto.request.UserUpdateRequest;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserStatusService userStatusService;

    @PostMapping(value="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestPart("userCreateRequest") UserCreateRequest userCreateRequest,
                                              @RequestPart(name = "profile", required = false) MultipartFile profileFile) {
        BinaryContentCreateRequest binaryContentCreateRequest = null;
        if (profileFile != null && !profileFile.isEmpty()) {
            try {
                binaryContentCreateRequest = BinaryContentCreateRequest.builder()
                        .fileName(profileFile.getOriginalFilename())
                        .contentType(profileFile.getContentType())
                        .bytes(profileFile.getBytes())
                        .build();
            } catch (IOException e) {
                // 파일 처리 중 문제가 발생하면 적절한 예외 처리를 합니다.
                throw new RuntimeException("Error processing profile file", e);
            }
        }

        User createdUser = userService.create(userCreateRequest, Optional.ofNullable(binaryContentCreateRequest));
        UserDto createdUserDto = userService.find(createdUser.getId());
        return ResponseEntity.ok(createdUserDto);

    }

    @PatchMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto>updateUser(
            @PathVariable("userId") UUID userId,
            @RequestPart("userUpdateRequest") UserUpdateRequest userUpdateRequest,
            @RequestPart(name="profile", required = false) MultipartFile profileFile){

        BinaryContentCreateRequest binaryContentCreateRequest = null;
        if (profileFile != null && !profileFile.isEmpty()) {
            try{
                binaryContentCreateRequest = BinaryContentCreateRequest.builder()
                        .fileName(profileFile.getOriginalFilename())
                        .contentType(profileFile.getContentType())
                        .bytes(profileFile.getBytes())
                        .build();
            }catch (IOException e){
                throw new RuntimeException("Error processing profile file", e);
            }
        }
        User updatedUser = userService.update(userId, userUpdateRequest, Optional.ofNullable(binaryContentCreateRequest));
        UserDto updatedUserDto = userService.find(updatedUser.getId());
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("userId") UUID userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/{userId}/userStatus")
    public ResponseEntity<UserDto> updateUserStatus(@PathVariable("userId")UUID userId,
                                                    @RequestBody UserStatusUpdateRequest userStatusUpdateRequest) {
        userStatusService.updateByUserId(userId, userStatusUpdateRequest);

        UserDto updatedUserDto = userService.find(userId);
        return ResponseEntity.ok(updatedUserDto);
    }

}
