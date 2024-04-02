package com.project.conferencescheduler.controller;

import com.project.conferencescheduler.model.Speaker;
import com.project.conferencescheduler.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speakers")
@RequiredArgsConstructor
public class SpeakerController {
    private final SpeakerService speakerService;
    
    @GetMapping
    public List<Speaker> listSpeakers() {
        return speakerService.listSpeakers();
    }
    
    @GetMapping("{speakerId}")
    public Speaker getSpeaker(@PathVariable("speakerId") Long speakerId) {
        return speakerService.get( speakerId );
    }
    
    
    @PostMapping
    public Speaker createSpeaker(@RequestBody Speaker speaker) {
        return speakerService.create(speaker);
    }
    
    @DeleteMapping("{speakerId}")
    public String deleteSpeaker(@PathVariable("speakerId") Long speakerId) {
        speakerService.delete(speakerId);
        return "Speaker deleted successfully";
    }
    
    @PutMapping("{speakerId}")
    public Speaker updateSpeaker(@PathVariable("speakerId") Long speakerId, @RequestBody Speaker speaker) {
        return speakerService.update(speakerId,speaker);
    }
}
