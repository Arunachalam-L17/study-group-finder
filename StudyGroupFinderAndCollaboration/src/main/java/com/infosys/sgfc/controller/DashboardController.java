package com.infosys.sgfc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infosys.sgfc.dto.DashboardResponse;
import com.infosys.sgfc.security.JwtUtil;
import com.infosys.sgfc.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:3000")

public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<?> getDashboard(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // remove "Bearer "
        String email = jwtUtil.extractEmail(token);

        DashboardResponse response =
                dashboardService.getDashboardData(email);

        return ResponseEntity.ok(response);
    }
}
