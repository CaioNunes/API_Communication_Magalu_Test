package com.magalu.communicationapi.converters;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;
import com.magalu.communicationapi.enums.CommunicationFormatEnum;
import com.magalu.communicationapi.enums.ScheduleStatusEnum;
import com.magalu.communicationapi.models.CommunicationScheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommunicationSchedulingConverter {

    public static CommunicationScheduling convertToEntity(CommunicationSchedulingDTO dto){
        CommunicationScheduling communicationScheduling = new CommunicationScheduling();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        //LocalDateTime dateTimeSubmission = LocalDateTime.parse(dto.getDateTimeSubmission(), formatter);

        communicationScheduling.setId(dto.getId());
        //communicationScheduling.setDateTimeSubmission(dateTimeSubmission);
        communicationScheduling.setDateTimeSubmission(dto.getDateTimeSubmission());
        communicationScheduling.setReceiver(dto.getReceiver());
        communicationScheduling.setMessage(dto.getMessage());
        communicationScheduling.setCommunicationFormat(CommunicationFormatEnum.findCommunicationFormatByDescription(dto.getCommunicationFormat()));
        communicationScheduling.setScheduleStatus(ScheduleStatusEnum.findScheduleStatusByDescription(dto.getScheduleStatus()));

        return communicationScheduling;
    }

    public static CommunicationSchedulingDTO convertToDTO(CommunicationScheduling entity){
        CommunicationSchedulingDTO communicationSchedulingDTO = new CommunicationSchedulingDTO();

        communicationSchedulingDTO.setId(entity.getId());
        //communicationSchedulingDTO.setDateTimeSubmission(entity.getDateTimeSubmission().toString());
        communicationSchedulingDTO.setDateTimeSubmission(entity.getDateTimeSubmission());
        communicationSchedulingDTO.setReceiver(entity.getReceiver());
        communicationSchedulingDTO.setMessage(entity.getMessage());
        communicationSchedulingDTO.setCommunicationFormat(entity.getCommunicationFormat().getDescription());
        communicationSchedulingDTO.setScheduleStatus(entity.getScheduleStatus().getDescription());

        return communicationSchedulingDTO;
    }

}
