package com.magalu.communicationapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magalu.communicationapi.enums.CommunicationFormatEnum;
import com.magalu.communicationapi.enums.ScheduleStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommunicationSchedulingDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("dateTimeSubmission")
    private String dateTimeSubmission;

    @JsonProperty("receiver")
    private String receiver;

    @JsonProperty("message")
    private String message;

    @JsonProperty("communicationFormat")
    private String communicationFormat;

    @JsonProperty("scheduleStatus")
    private String scheduleStatus;

}
