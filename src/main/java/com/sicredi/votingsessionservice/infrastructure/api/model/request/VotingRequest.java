package com.sicredi.votingsessionservice.infrastructure.api.model.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotingRequest {
    private String userCode;
    private String assemblyCode;
    private Boolean voting;
}
