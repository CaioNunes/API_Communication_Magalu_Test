package com.magalu.communicationapi.models;

import com.magalu.communicationapi.enums.CommunicationFormatEnum;
import com.magalu.communicationapi.enums.ScheduleStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="COMMUNICATION_SCHEDULING")
@Getter
@Setter
public class CommunicationScheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

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
