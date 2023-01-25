package com.neylandev.delivery.domain.repository;

import com.neylandev.delivery.domain.model.Client;
import com.neylandev.delivery.domain.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    Optional<Client> findByClientEmail(String clientEmail);
}
