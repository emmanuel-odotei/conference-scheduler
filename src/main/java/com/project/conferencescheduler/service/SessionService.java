package com.project.conferencescheduler.service;

import com.project.conferencescheduler.exception.AppException;
import com.project.conferencescheduler.model.Session;
import com.project.conferencescheduler.model.SessionRequestDto;
import com.project.conferencescheduler.model.Speaker;
import com.project.conferencescheduler.repository.SessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SpeakerService speakerService;
    
    public List<Session> listSessions () {
        return sessionRepository.findAll();
    }
    
    public Session get (Long sessionId) {
        return sessionRepository.findById( sessionId ).orElseThrow( () -> new AppException( "Session not found" ) );
    }
    
    @Transactional
    public Session create (SessionRequestDto sessionRequest) {
        List<Speaker> speakers = speakerService.getSpeakersByIds( sessionRequest.getSpeakerIds() );
        
        Session newSession = sessionRequest.getSession();
        newSession.setSpeakers( speakers );
        return sessionRepository.save( newSession );
    }
    
    @Transactional
    public void delete (Long sessionId) {
        boolean exists = sessionRepository.existsById( sessionId );
        if ( !exists ) throw new AppException( "Session not found" );
        sessionRepository.deleteById( sessionId );
    }
    
    @Transactional
    public Session update (Long sessionId, SessionRequestDto sessionRequest) {
        Session input = sessionRequest.getSession();
        Session existingSession = get( sessionId );
        existingSession.setSessionName( input.getSessionName() != null ? input.getSessionName() : existingSession.getSessionName() );
        existingSession.setSessionDescription( input.getSessionDescription() != null ? input.getSessionDescription() : existingSession.getSessionDescription() );
        existingSession.setSessionLength( input.getSessionLength() != null ? input.getSessionLength() : existingSession.getSessionLength() );
        
        List<Speaker> speakers = speakerService.getSpeakersByIds( sessionRequest.getSpeakerIds() );
        existingSession.setSpeakers( sessionRequest.getSpeakerIds() != null ? speakers : existingSession.getSpeakers() );
        return sessionRepository.save( existingSession );
    }
}
