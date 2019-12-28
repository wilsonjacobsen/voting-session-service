package com.sicredi.votingsessionservice.infrastructure.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

public class VotingEntity {
    @Id
    private String id;
    private String userCode;
    private Date date;
    @Indexed
    private String assemblyCode;
    private Boolean voting;
}
