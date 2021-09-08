package com.magalu.communicationapi.controllers;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;
import com.magalu.communicationapi.enums.CommunicationFormatEnum;
import com.magalu.communicationapi.enums.ScheduleStatusEnum;
import com.magalu.communicationapi.models.CommunicationScheduling;
import com.magalu.communicationapi.services.CommunicationSchedulingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.magalu.communicationapi.util.ResponseMessages.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunicationSchedulingControllerTest {

    @Mock
    CommunicationSchedulingService service;

    @InjectMocks
    CommunicationSchedulingController controller;

    private final String RECEIVER = "RECEIVER";
    private final String MESSAGE = "MESSAGE";
    private final String FORMAT = "sms";

    @Test
    public void testScheduleCommunicationWithSuccess(){
        CommunicationSchedulingDTO requestMock = generateCommunicationSchedulingDTOWithoutIdAndScheduleStatusMock();
        CommunicationSchedulingDTO responseMock = generateCommunicationSchedulingDTOMock();

        when(service.scheduleCommunication(any())).thenReturn(responseMock);
        ResponseEntity result = controller.scheduleCommunication(requestMock);

        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        assertEquals(responseMock.getId(), ((CommunicationSchedulingDTO) result.getBody()).getId());
        assertEquals(responseMock.getDateTimeSubmission(), ((CommunicationSchedulingDTO) result.getBody()).getDateTimeSubmission());
        assertEquals(responseMock.getReceiver(), ((CommunicationSchedulingDTO) result.getBody()).getReceiver());
        assertEquals(responseMock.getMessage(), ((CommunicationSchedulingDTO) result.getBody()).getMessage());
        assertEquals(responseMock.getCommunicationFormat(), CommunicationFormatEnum.findCommunicationFormatByDescription(((CommunicationSchedulingDTO) result.getBody()).getCommunicationFormat()).getDescription());
        assertEquals(ScheduleStatusEnum.SCHEDULED, ScheduleStatusEnum.findScheduleStatusByDescription(((CommunicationSchedulingDTO) result.getBody()).getScheduleStatus()));

    }

    @Test
    public void testGetCommunicationSchedulesWithReturn(){
        CommunicationSchedulingDTO responseMock = generateCommunicationSchedulingDTOMock();
        List<CommunicationSchedulingDTO> resultsMock = new ArrayList<>();
        resultsMock.add(responseMock);

        when(service.getCommunicationSchedules()).thenReturn(resultsMock);
        ResponseEntity result = controller.getCommunicationSchedule();

        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        List<CommunicationSchedulingDTO> resultBody = ((List<CommunicationSchedulingDTO>) result.getBody());

        assertEquals(1, resultBody.size());
        assertEquals(responseMock.getId(), resultBody.get(0).getId());
        assertEquals(responseMock.getDateTimeSubmission(), resultBody.get(0).getDateTimeSubmission());
        assertEquals(responseMock.getReceiver(), resultBody.get(0).getReceiver());
        assertEquals(responseMock.getMessage(), resultBody.get(0).getMessage());
        assertEquals(responseMock.getCommunicationFormat(), CommunicationFormatEnum.findCommunicationFormatByDescription(resultBody.get(0).getCommunicationFormat()).getDescription());
        assertEquals(ScheduleStatusEnum.SCHEDULED, ScheduleStatusEnum.findScheduleStatusByDescription(resultBody.get(0).getScheduleStatus()));
    }

    @Test
    public void testGetCommunicationSchedulesWithoutReturn(){
        when(service.getCommunicationSchedules()).thenReturn(null);
        ResponseEntity result = controller.getCommunicationSchedule();

        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(NO_COMMUNICATION_SCHEDULES_FOUND, (String) result.getBody());
    }


    @Test
    public void testGetCommunicationScheduleByIdWithReturn(){
        CommunicationSchedulingDTO responseMock = generateCommunicationSchedulingDTOMock();

        when(service.getCommunicationSchedule(any())).thenReturn(responseMock);
        ResponseEntity result = controller.getCommunicationSchedule(1l);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertNotNull(result);

        CommunicationSchedulingDTO resultBody = ((CommunicationSchedulingDTO) result.getBody());

        assertEquals(responseMock.getId(), resultBody.getId());
        assertEquals(responseMock.getDateTimeSubmission(), resultBody.getDateTimeSubmission());
        assertEquals(responseMock.getReceiver(), resultBody.getReceiver());
        assertEquals(responseMock.getMessage(), resultBody.getMessage());
        assertEquals(responseMock.getCommunicationFormat(), CommunicationFormatEnum.findCommunicationFormatByDescription(resultBody.getCommunicationFormat()).getDescription());
        assertEquals(ScheduleStatusEnum.SCHEDULED, ScheduleStatusEnum.findScheduleStatusByDescription(resultBody.getScheduleStatus()));
    }

    @Test
    public void testGetCommunicationScheduleByIdWithoutReturn(){
        when(service.getCommunicationSchedule(1l)).thenReturn(null);
        ResponseEntity result = controller.getCommunicationSchedule(1l);

        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(NO_COMMUNICATION_SCHEDULE_FOUND, (String) result.getBody());
    }

    @Test
    public void testCancelCommunicationScheduleWithSuccess(){
        when(service.cancelCommunicationSchedule(any())).thenReturn(true);
        ResponseEntity result = controller.cancelCommunicationSchedule(1l);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(COMMUNICATION_SCHEDULE_SUCESSFULLY_CANCELED, (String) result.getBody());

    }

    @Test
    public void testCancelCommunicationScheduleWithoutSuccess(){
        when(service.cancelCommunicationSchedule(1l)).thenReturn(false);
        ResponseEntity result = controller.cancelCommunicationSchedule(1l);

        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(NO_COMMUNICATION_SCHEDULE_FOUND, (String) result.getBody());

    }

    private CommunicationSchedulingDTO generateCommunicationSchedulingDTOWithoutIdAndScheduleStatusMock(){
        CommunicationSchedulingDTO communicationSchedulingDTO = new CommunicationSchedulingDTO();

        communicationSchedulingDTO.setDateTimeSubmission(LocalDateTime.now());
        communicationSchedulingDTO.setReceiver(RECEIVER);
        communicationSchedulingDTO.setMessage(MESSAGE);
        communicationSchedulingDTO.setCommunicationFormat(FORMAT);

        return communicationSchedulingDTO;
    }

    private CommunicationSchedulingDTO generateCommunicationSchedulingDTOMock(){
        CommunicationSchedulingDTO communicationScheduling = new CommunicationSchedulingDTO();

        communicationScheduling.setId(1l);
        communicationScheduling.setDateTimeSubmission(LocalDateTime.now());
        communicationScheduling.setReceiver(RECEIVER);
        communicationScheduling.setMessage(MESSAGE);
        communicationScheduling.setCommunicationFormat(CommunicationFormatEnum.SMS.getDescription());
        communicationScheduling.setScheduleStatus(ScheduleStatusEnum.SCHEDULED.getDescription());

        return communicationScheduling;
    }

    private CommunicationScheduling generateCommunicationSchedulingMock(){
        CommunicationScheduling communicationScheduling = new CommunicationScheduling();

        communicationScheduling.setId(1l);
        communicationScheduling.setDateTimeSubmission(LocalDateTime.now());
        communicationScheduling.setReceiver(RECEIVER);
        communicationScheduling.setMessage(MESSAGE);
        communicationScheduling.setCommunicationFormat(CommunicationFormatEnum.SMS);
        communicationScheduling.setScheduleStatus(ScheduleStatusEnum.SCHEDULED);

        return communicationScheduling;
    }
}
