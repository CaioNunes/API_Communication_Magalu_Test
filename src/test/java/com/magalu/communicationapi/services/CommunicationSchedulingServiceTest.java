package com.magalu.communicationapi.services;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;
import com.magalu.communicationapi.enums.CommunicationFormatEnum;
import com.magalu.communicationapi.enums.ScheduleStatusEnum;
import com.magalu.communicationapi.models.CommunicationScheduling;
import com.magalu.communicationapi.repositories.CommunicationSchedulingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunicationSchedulingServiceTest {

    @Mock
    CommunicationSchedulingRepository repository;

    @InjectMocks
    CommunicationSchedulingServiceImpl service;

    private final String RECEIVER = "RECEIVER";
    private final String MESSAGE = "MESSAGE";
    private final String FORMAT = "sms";

    @Test
    public void testScheduleCommunicationWithReturn(){
        CommunicationSchedulingDTO communicationSchedulingDTOMock = generateCommunicationSchedulingDTOWithoutIdAndScheduleStatusMock();
        CommunicationScheduling responseMock = generateCommunicationSchedulingMock();

        when(repository.save(any())).thenReturn(responseMock);
        CommunicationSchedulingDTO result = service.scheduleCommunication(communicationSchedulingDTOMock);

        assertNotNull(result);
        assertEquals(responseMock.getId(), result.getId());
        assertEquals(responseMock.getDateTimeSubmission(), result.getDateTimeSubmission());
        assertEquals(responseMock.getReceiver(), result.getReceiver());
        assertEquals(responseMock.getMessage(), result.getMessage());
        assertEquals(responseMock.getCommunicationFormat(), CommunicationFormatEnum.findCommunicationFormatByDescription(result.getCommunicationFormat()));
        assertEquals(ScheduleStatusEnum.SCHEDULED, ScheduleStatusEnum.findScheduleStatusByDescription(result.getScheduleStatus()));

    }

    @Test
    public void testGetCommunicationSchedulesWithReturn(){
        CommunicationScheduling responseMock = generateCommunicationSchedulingMock();
        List<CommunicationScheduling> resultsMock = new ArrayList<>();
        resultsMock.add(responseMock);

        when(repository.findAll()).thenReturn(resultsMock);
        List<CommunicationSchedulingDTO> result = service.getCommunicationSchedules();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseMock.getId(), result.get(0).getId());
        assertEquals(responseMock.getDateTimeSubmission(), result.get(0).getDateTimeSubmission());
        assertEquals(responseMock.getReceiver(), result.get(0).getReceiver());
        assertEquals(responseMock.getMessage(), result.get(0).getMessage());
        assertEquals(responseMock.getCommunicationFormat(), CommunicationFormatEnum.findCommunicationFormatByDescription(result.get(0).getCommunicationFormat()));
        assertEquals(ScheduleStatusEnum.SCHEDULED, ScheduleStatusEnum.findScheduleStatusByDescription(result.get(0).getScheduleStatus()));
    }

    @Test
    public void testGetCommunicationSchedulesWithoutReturn(){
        when(repository.findAll()).thenReturn(null);
        List<CommunicationSchedulingDTO> result = service.getCommunicationSchedules();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetCommunicationScheduleByIdWithReturn(){
        CommunicationScheduling responseMock = generateCommunicationSchedulingMock();

        when(repository.findById(any())).thenReturn(Optional.of(responseMock));
        CommunicationSchedulingDTO result = service.getCommunicationSchedule(1l);

        assertNotNull(result);
        assertEquals(responseMock.getId(), result.getId());
        assertEquals(responseMock.getDateTimeSubmission(), result.getDateTimeSubmission());
        assertEquals(responseMock.getReceiver(), result.getReceiver());
        assertEquals(responseMock.getMessage(), result.getMessage());
        assertEquals(responseMock.getCommunicationFormat(), CommunicationFormatEnum.findCommunicationFormatByDescription(result.getCommunicationFormat()));
        assertEquals(ScheduleStatusEnum.SCHEDULED, ScheduleStatusEnum.findScheduleStatusByDescription(result.getScheduleStatus()));
    }

    @Test
    public void testGetCommunicationScheduleByIdWithoutReturn(){
        when(repository.findById(1l)).thenReturn(Optional.ofNullable(null));
        CommunicationSchedulingDTO result = service.getCommunicationSchedule(1l);

        assertNull(result);
    }

    @Test
    public void testCancelCommunicationScheduleWithSuccess(){
        CommunicationScheduling responseMock = generateCommunicationSchedulingMock();

        when(repository.findById(any())).thenReturn(Optional.of(responseMock));
        boolean result = service.cancelCommunicationSchedule(1l);

        assertTrue(result);

    }

    @Test
    public void testCancelCommunicationScheduleWithoutSuccess(){
        when(repository.findById(1l)).thenReturn(Optional.ofNullable(null));
        boolean result = service.cancelCommunicationSchedule(1l);

        assertFalse(result);

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
