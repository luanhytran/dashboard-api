package com.luanhydev.dashboard.auth;

import com.luanhydev.dashboard.appuser.AppUser;
import com.luanhydev.dashboard.appuser.AppUserRepository;
import com.luanhydev.dashboard.auth.payload.LoginRequest;
import com.luanhydev.dashboard.auth.payload.MessageResponse;
import com.luanhydev.dashboard.auth.payload.UserInfoResponse;
import com.luanhydev.dashboard.auth.registration.EmailValidator;
import com.luanhydev.dashboard.auth.registration.RegistrationService;
import com.luanhydev.dashboard.auth.registration.SignupRequest;
import com.luanhydev.dashboard.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser userDetails = (AppUser) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        boolean isValidEmail = emailValidator.
                test(signUpRequest.getEmail());

        if (!isValidEmail) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email not valid"));
        }

         registrationService.register(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully! Please check your email to confirm account"));
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
