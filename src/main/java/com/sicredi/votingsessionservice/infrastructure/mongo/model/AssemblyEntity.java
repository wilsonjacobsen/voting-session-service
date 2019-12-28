package com.sicredi.votingsessionservice.infrastructure.mongo.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.swing.*;
import java.util.Date;


@Document("assembly")
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyEntity {
    private Spring code;
    private Spring name;
    private Date startDate;
    private Date finishDate;
}
