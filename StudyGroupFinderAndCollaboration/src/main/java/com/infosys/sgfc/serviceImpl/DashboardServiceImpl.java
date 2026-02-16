package com.infosys.sgfc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.sgfc.dto.DashboardResponse;
import com.infosys.sgfc.model.Users;
import com.infosys.sgfc.repository.AuthRepository;
import com.infosys.sgfc.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public DashboardResponse getDashboardData(String email) {

        Users user = authRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        return new DashboardResponse(
                user.getFullName(),
                user.getEmail(),
                user.getDepartment(),
                user.getSkills(),
                "Welcome to your dashboard!"
        );
    }
}
