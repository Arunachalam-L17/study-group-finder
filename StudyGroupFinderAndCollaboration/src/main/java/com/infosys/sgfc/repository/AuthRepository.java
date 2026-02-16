package com.infosys.sgfc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.sgfc.model.Users;

@Repository
public interface AuthRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByEmail(String email);

}
