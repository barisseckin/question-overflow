package com.developertools.questionoverflow.controller;

import com.developertools.questionoverflow.dto.UserDto;
import com.developertools.questionoverflow.dto.request.CreateUserRequest;
import com.developertools.questionoverflow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody @Valid CreateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(request));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String mail) {
        userService.deleteByMail(mail);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<UserDto> activate(@RequestParam String mail, @RequestParam int code) {
        return ResponseEntity
                .ok(userService.activateUser(mail, code));
    }

    @GetMapping("/{mail}")
    public ResponseEntity<UserDto> getByMail(@PathVariable(value = "mail") String mail) {
        return ResponseEntity
                .ok(userService.getByUserMail(mail));
    }

    @PostMapping("/send-activate-code")
    public ResponseEntity<?> sendActivateCode(@RequestParam String mail) {
        userService.sendActivateCode(mail);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-notification-permission")
    public ResponseEntity<UserDto> updateNotificationPermissionByMail(@RequestParam String mail,
                                                                      @RequestParam boolean permission) {
        return ResponseEntity
                .ok(userService.updateNotificationPermissionByMail(mail, permission));
    }
}
