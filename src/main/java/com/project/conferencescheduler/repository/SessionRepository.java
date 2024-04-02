package com.project.conferencescheduler.repository;

import com.project.conferencescheduler.model.Session;
import com.project.conferencescheduler.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findBySpeakersContaining (Speaker speakerId);
}
