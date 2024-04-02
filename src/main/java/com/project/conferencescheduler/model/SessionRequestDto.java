package com.project.conferencescheduler.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SessionRequestDto {
    private Session session;
    private List<Long>speakerIds;
}
