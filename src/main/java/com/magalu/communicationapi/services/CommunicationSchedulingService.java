package com.magalu.communicationapi.services;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;
import com.magalu.communicationapi.converters.CommunicationSchedulingConverter;
import com.magalu.communicationapi.enums.ScheduleStatusEnum;
import com.magalu.communicationapi.models.CommunicationScheduling;
import com.magalu.communicationapi.repositories.CommunicationSchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommunicationSchedulingService {

    @Autowired
    CommunicationSchedulingRepository repository;

    public CommunicationSchedulingDTO scheduleCommunication(CommunicationSchedulingDTO communicationSchedulingDTO){
        communicationSchedulingDTO.setScheduleStatus(ScheduleStatusEnum.SCHEDULED.getDescription());

        CommunicationScheduling communicationScheduling =  repository.save(CommunicationSchedulingConverter.convertToEntity(communicationSchedulingDTO));

        return CommunicationSchedulingConverter.convertToDTO(communicationScheduling);
    }

    public List<CommunicationSchedulingDTO> getCommunicationSchedules(){
        List<CommunicationScheduling> communicationSchedules = repository.findAll();

        List<CommunicationSchedulingDTO> communicationSchedulesDTO = new ArrayList<>();

        if(communicationSchedules != null){
            for(CommunicationScheduling scheduling: communicationSchedules){
                CommunicationSchedulingDTO schedulingDTO = CommunicationSchedulingConverter.convertToDTO(scheduling);
                communicationSchedulesDTO.add(schedulingDTO);
            }
        }

        return communicationSchedulesDTO;
    }

    public CommunicationSchedulingDTO getCommunicationScheduling(Long id){
        Optional<CommunicationScheduling> communicationSchedulingData = repository.findById(id);

        if(communicationSchedulingData.isPresent()){
            return CommunicationSchedulingConverter.convertToDTO(communicationSchedulingData.get());
        } else {
            return null;
        }

    }

    public boolean cancelCommunicationScheduling(Long id){
        boolean cancelled = false;
        Optional<CommunicationScheduling> communicationSchedulingData = repository.findById(id);

        if(communicationSchedulingData.isPresent()){
            CommunicationScheduling communicationScheduling = communicationSchedulingData.get();
            communicationScheduling.setScheduleStatus(ScheduleStatusEnum.CANCELLED);
            repository.save(communicationScheduling);

            cancelled = true;
        }

        return cancelled;

    }




}
