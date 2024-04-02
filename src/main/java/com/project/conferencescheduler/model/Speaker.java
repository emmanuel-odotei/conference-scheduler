package com.project.conferencescheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "speakers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Speaker {
    @Id
    @SequenceGenerator( name = "speaker_sequence", allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "speaker_sequence")
    private Long speakerId;
    private String firstName;
    private String lastName;
    private String title;
    private String company;
    private String speakerBio;
    private String speakerPhoto;
    
    @ManyToMany( mappedBy = "speakers", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JsonIgnore
    private List<Session> sessions;
}
