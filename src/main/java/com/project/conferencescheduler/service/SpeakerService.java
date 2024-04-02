package com.project.conferencescheduler.service;

import com.project.conferencescheduler.exception.AppException;
import com.project.conferencescheduler.model.Session;
import com.project.conferencescheduler.model.Speaker;
import com.project.conferencescheduler.repository.SessionRepository;
import com.project.conferencescheduler.repository.SpeakerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeakerService {
    private final SpeakerRepository speakerRepository;
    private final SessionRepository sessionRepository;
    
    public List<Speaker> listSpeakers () {
        return speakerRepository.findAll();
    }
    
    public Speaker get (Long speakerId) {
        return speakerRepository.findById( speakerId ).orElseThrow( () -> new AppException( "Speaker not found" ) );
    }
    
    public Speaker create (Speaker speaker) {
        return speakerRepository.save( speaker );
    }
    
    @Transactional
    public void delete (Long speakerId) {
        Speaker existingSpeaker = speakerRepository.findById( speakerId ).orElseThrow( () -> new AppException( "Speaker not found" ) );
        
        List<Session> sessions = sessionRepository.findBySpeakersContaining( existingSpeaker );
        for ( Session session : sessions ) {
            session.getSpeakers().remove( existingSpeaker );
            sessionRepository.save( session );
        }
        speakerRepository.deleteById( speakerId );
    }
    
    @Transactional
    public Speaker update (Long speakerId, Speaker speaker) {
        Speaker existingSpeaker = get( speakerId );
        existingSpeaker.setFirstName( speaker.getFirstName() != null ? speaker.getFirstName() : existingSpeaker.getFirstName() );
        existingSpeaker.setLastName( speaker.getLastName() != null ? speaker.getLastName() : existingSpeaker.getLastName() );
        existingSpeaker.setTitle( speaker.getTitle() != null ? speaker.getTitle() : existingSpeaker.getTitle() );
        existingSpeaker.setCompany( speaker.getCompany() != null ? speaker.getCompany() : existingSpeaker.getCompany() );
        existingSpeaker.setSpeakerBio( speaker.getSpeakerBio() != null ? speaker.getSpeakerBio() : existingSpeaker.getSpeakerBio() );
        existingSpeaker.setSpeakerPhoto( speaker.getSpeakerPhoto() != null ? speaker.getSpeakerPhoto() : existingSpeaker.getSpeakerPhoto() );
        existingSpeaker.setSessions( speaker.getSessions() != null ? speaker.getSessions() : existingSpeaker.getSessions() );
        return speakerRepository.save( existingSpeaker );
    }
    
    @Transactional
    public List<Speaker> getSpeakersByIds (List<Long> speakerIds) {
        return speakerRepository.findAllById( speakerIds );
    }
}
