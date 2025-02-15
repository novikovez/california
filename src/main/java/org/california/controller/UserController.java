package org.california.controller;

import org.california.exception.GlobalExceptionHandler;
import org.california.exception.user.UserExistsException;
import org.california.exception.user.UserRoleException;
import org.california.response.Response;
import org.california.service.user.UserInfoService;
import org.california.entity.user.UserInfo;
import org.california.dto.user.AuthRequestDto;
import org.california.response.user.TokenExpired;
import org.california.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("api/v1/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to not secure page";
    }

    @GetMapping("/add_new_user")
    public ResponseEntity<Map<String, Object>> addNewUser(@RequestBody UserInfo userInfo) {
        try {
            String userPassword = userInfo.getPassword();
            UserInfo userSaveResult = service.addUser(userInfo);
            userSaveResult.setPassword(userPassword);
            return new Response<UserInfo>().responseEntity(true, userSaveResult, HttpStatus.CREATED);
        } catch (UserRoleException | UserExistsException e) {
            System.err.println(e.getMessage());
            return new Response<String>().responseEntity(false, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAuthority(T(org.california.enums.UserRole).ROLE_USER.name())")
    public String userProfile() {
        return "Welcome User Profile";
    }

    @GetMapping("/admin/profile")
    @PreAuthorize("hasAuthority('ROLE_' + T(org.california.enums.UserRole).ROLE_ADMIN.name())")
    public String adminProfile() {
        return "Welcome Admin Profile";
    }

    @PostMapping("/generateToken")
    public TokenExpired authenticateAndGetToken(@RequestBody AuthRequestDto authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                            authRequest.getPassword())
            );
            if(authentication.isAuthenticated()) {
                return jwtService.generateToken(authRequest.getEmail());
            }
            throw new UsernameNotFoundException("Invalid username");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/check_exp")
    public TokenExpired checkToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        return new TokenExpired(token, jwtService.extractExpiration(token));
    }
}
