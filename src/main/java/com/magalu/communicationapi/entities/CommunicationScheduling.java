package com.magalu.communicationapi.entities;

import com.magalu.communicationapi.enums.CommunicationFormatEnum;
import com.magalu.communicationapi.enums.ScheduleStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Repository
@Table(name="COMMUNICATION_SCHEDULING")
@Getter
@Setter
public class CommunicationScheduling {

    @Column(name="DATE_TIME_SUBMISSION")
    private LocalDateTime dateTimeSubmission;

    @Column(name="RECEIVER")
    private String receiver;

    @Column(name="MESSAGE")
    private String message;

    @Column(name="COMMUNICATION_FORMAT")
    @Enumerated(EnumType.STRING)
    private CommunicationFormatEnum communicationFormat;

    @Column(name="SCHEDULE_STATUS")
    @Enumerated(EnumType.STRING)
    private ScheduleStatusEnum scheduleStatus;

}
