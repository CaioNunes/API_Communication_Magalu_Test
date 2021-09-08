package com.magalu.communicationapi.controllers;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;
import com.magalu.communicationapi.services.CommunicationSchedulingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.magalu.communicationapi.util.ResponseMessages.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/communication-magalu/api", produces = "application/json")
public class CommunicationSchedulingController {

    @Autowired
    CommunicationSchedulingService communicationSchedulingService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = COMMUNICATION_SCHEDULING_SUCCESFULLY_REGISTERED, response = CommunicationSchedulingDTO.class),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
    })
    @ApiOperation(value = "Schedule a communication.")
    @PostMapping("/scheduleCommunication")
    public ResponseEntity scheduleCommunication(@RequestBody @Valid CommunicationSchedulingDTO communicationSchedulingDTO){
        CommunicationSchedulingDTO registered = communicationSchedulingService.scheduleCommunication(communicationSchedulingDTO);

        try {
            return new ResponseEntity<>(registered, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = CommunicationSchedulingDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = NO_COMMUNICATION_SCHEDULES_FOUND),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
    })
    @ApiOperation(value = "Return all communication schedules.")
    @GetMapping("/communicationSchedules")
    public ResponseEntity getCommunicationSchedule(){
        try{
            List<CommunicationSchedulingDTO> communicationSchedules = communicationSchedulingService.getCommunicationSchedules();

            if(communicationSchedules != null && !communicationSchedules.isEmpty()){
                return new ResponseEntity<>(communicationSchedules, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(NO_COMMUNICATION_SCHEDULES_FOUND, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = CommunicationSchedulingDTO.class),
            @ApiResponse(code = 404, message = NO_COMMUNICATION_SCHEDULE_FOUND),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
    })
    @ApiOperation(value = "Return a communication schedule by id.")
    @GetMapping("/communicationScheduling/{id}")
    public ResponseEntity getCommunicationSchedule(@PathVariable Long id){
        try{
            CommunicationSchedulingDTO communicationScheduling = communicationSchedulingService.getCommunicationSchedule(id);

            if(communicationScheduling != null) {
                return new ResponseEntity<>(communicationScheduling, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(NO_COMMUNICATION_SCHEDULE_FOUND, HttpStatus.NOT_FOUND);
            }


        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = COMMUNICATION_SCHEDULE_SUCESSFULLY_CANCELED, response = String.class),
            @ApiResponse(code = 404, message = NO_COMMUNICATION_SCHEDULE_FOUND),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
    })
    @ApiOperation(value = "Cancel a communication schedule by id.")
    @PatchMapping("/cancelCommunicationScheduling/{id}")
    public ResponseEntity cancelCommunicationSchedule(@PathVariable Long id){
        try{
            boolean cancelled = communicationSchedulingService.cancelCommunicationSchedule(id);

            if(cancelled) {
                return new ResponseEntity<>(COMMUNICATION_SCHEDULE_SUCESSFULLY_CANCELED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(NO_COMMUNICATION_SCHEDULE_FOUND, HttpStatus.NOT_FOUND);
            }


        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
