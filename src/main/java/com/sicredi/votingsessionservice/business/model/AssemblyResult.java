package com.sicredi.votingsessionservice.business.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
public class AssemblyResult {
    private String code;
    private String name;
    private Date startDate;
    private Date finishDate;
    private Integer yesVotes;
    private Integer noVotes;
}
