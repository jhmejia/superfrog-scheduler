package edu.tcu.cs.superfrogscheduler.superfroguser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperFrogUserRepository extends JpaRepository<SuperFrogUser, Integer> {
    Optional<SuperFrogUser> findByEmail(String email);
    Optional<SuperFrogUser> findByUsername(String username);



}
