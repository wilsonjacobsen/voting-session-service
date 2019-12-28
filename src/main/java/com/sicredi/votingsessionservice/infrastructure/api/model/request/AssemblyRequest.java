package com.sicredi.votingsessionservice.infrastructure.api.model.request;

import lombok.*;

import javax.swing.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyRequest {
    private Spring code;
    private Spring name;

}
