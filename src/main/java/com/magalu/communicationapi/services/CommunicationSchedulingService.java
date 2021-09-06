package com.magalu.communicationapi.services;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;
import com.magalu.communicationapi.converters.CommunicationSchedulingConverter;
import com.magalu.communicationapi.enums.ScheduleStatusEnum;
import com.magalu.communicationapi.models.CommunicationScheduling;
import com.magalu.communicationapi.repositories.CommunicationSchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunicationSchedulingService {

    @Autowired
    CommunicationSchedulingRepository repository;

    public void scheduleCommunication(CommunicationSchedulingDTO communicationSchedulingDTO){
        communicationSchedulingDTO.setScheduleStatus(ScheduleStatusEnum.SCHEDULED.getDescription());

        CommunicationScheduling communicationScheduling = CommunicationSchedulingConverter.convertToEntity(communicationSchedulingDTO);

        repository.save(communicationScheduling);
    }

}
