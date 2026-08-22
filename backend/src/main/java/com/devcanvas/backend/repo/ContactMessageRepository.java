package com.devcanvas.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcanvas.backend.entity.ContactMessage;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

}