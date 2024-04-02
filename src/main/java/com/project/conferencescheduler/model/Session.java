package com.project.conferencescheduler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity( name = "sessions" )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Session {
    @Id
    @SequenceGenerator( name = "session_sequence", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "session_sequence" )
    private Long sessionId;
    private String sessionName;
    private String sessionDescription;
    private Integer sessionLength;
    
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<Speaker> speakers;
}
