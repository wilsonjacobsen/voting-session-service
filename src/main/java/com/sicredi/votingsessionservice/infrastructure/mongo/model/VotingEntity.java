package com.sicredi.votingsessionservice.infrastructure.mongo.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Getter
@Builder
public class VotingEntity {
    @Id
    private String id;
    private String userCode;
    private Date date;
    @Indexed
    private String assemblyCode;
    private Boolean voting;
}
