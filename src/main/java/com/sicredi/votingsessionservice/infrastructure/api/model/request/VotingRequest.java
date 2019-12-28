package com.sicredi.votingsessionservice.infrastructure.api.model.request;

import java.util.Date;

public class VotingRequest {
    private String userCode;
    private Date date;
    private String assemblyCode;
    private Boolean voting;
}
