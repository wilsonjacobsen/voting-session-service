package com.sicredi.votingsessionservice.infrastructure.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VotingResponse {
    private String id;
    private String userCode;
    private String assemblyCode;
    private Date date;
}
