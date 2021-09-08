package com.magalu.communicationapi.services;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;

import java.util.List;

public interface CommunicationSchedulingService {

    CommunicationSchedulingDTO scheduleCommunication(CommunicationSchedulingDTO communicationSchedulingDTO);

    List<CommunicationSchedulingDTO> getCommunicationSchedules();

    CommunicationSchedulingDTO getCommunicationSchedule(Long id);

    boolean cancelCommunicationSchedule(Long id);
}
