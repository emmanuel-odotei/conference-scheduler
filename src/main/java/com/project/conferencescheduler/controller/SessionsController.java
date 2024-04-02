package com.project.conferencescheduler.controller;

import com.project.conferencescheduler.model.Session;
import com.project.conferencescheduler.model.SessionRequestDto;
import com.project.conferencescheduler.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionsController {
    private final SessionService sessionService;
    
    @GetMapping
    public List<Session> listSessions() {
        return sessionService.listSessions();
    }
    
    @GetMapping("{sessionId}")
    public Session getSession(@PathVariable( "sessionId" ) Long sessionId) {
        return sessionService.get(sessionId);
    }
    
    @PostMapping()
    public Session createSession(@RequestBody SessionRequestDto sessionRequest) {
        return sessionService.create(sessionRequest);
    }
    
    @DeleteMapping("{sessionId}")
    public String deleteSession(@PathVariable("sessionId") Long sessionId) {
        sessionService.delete(sessionId);
        return "Session deleted successfully";
    }
    
    @PutMapping("{sessionId}")
    public Session updateSession(@PathVariable("sessionId") Long sessionId, @RequestBody SessionRequestDto sessionRequest) {
        return sessionService.update(sessionId,sessionRequest);
    }
}
