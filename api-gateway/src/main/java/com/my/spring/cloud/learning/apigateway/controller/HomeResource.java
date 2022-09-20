package com.my.spring.cloud.learning.apigateway.controller;


import com.my.spring.cloud.learning.apigateway.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/")
    public String home() {
        return "<h1>Welcome</h1>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h1>Welcome User</h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1>Welcome Admin</h1>";
    }

//
//    @PostMapping("/authenticate")
//    public ResponseEntity<JwtResponse> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//
//        final String username = authenticationRequest.getUsername();
//        final String password = authenticationRequest.getPassword();
//
//        Objects.requireNonNull(username);
//        Objects.requireNonNull(password);
//
//        try {
//
//            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//        } catch (Exception ex) {
//            throw new Exception("USER_DISABLED or INVALID_CREDENTIALS", ex);
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        final String jwtToken = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new JwtResponse(jwtToken));
//    }
}
