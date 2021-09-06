package com.magalu.communicationapi.controllers;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;
import com.magalu.communicationapi.services.CommunicationSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/communication-magalu/api", produces = "application/json")
public class CommunicationSchedulingController {

    @Autowired
    CommunicationSchedulingService communicationSchedulingService;

    @PostMapping("/scheduleCommunication")
    public ResponseEntity<String> scheduleCommunication(@RequestBody CommunicationSchedulingDTO communicationSchedulingDTO){
        communicationSchedulingService.scheduleCommunication(communicationSchedulingDTO);

        return new ResponseEntity<>("Communication Scheduling successfully registered !", HttpStatus.CREATED);
    }

    @GetMapping("/communicationSchedules")
    public ResponseEntity getCommunicationSchedules(){
        try{
            List<CommunicationSchedulingDTO> communicationSchedules = communicationSchedulingService.getCommunicationSchedules();

            if(communicationSchedules != null && !communicationSchedules.isEmpty()){
                return new ResponseEntity<>(communicationSchedules, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No communication schedules found !", HttpStatus.OK);
            }

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/communicationScheduling/{id}")
    public ResponseEntity getCommunicationSchedules(@PathVariable Long id){
        try{
            CommunicationSchedulingDTO communicationScheduling = communicationSchedulingService.getCommunicationScheduling(id);

            if(communicationScheduling != null) {
                return new ResponseEntity<>(communicationScheduling, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No communication schedule found !", HttpStatus.OK);
            }


        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/cancelCommunicationScheduling/{id}")
    public ResponseEntity cancelCommunicationSchedule(@PathVariable Long id){
        try{
            boolean cancelled = communicationSchedulingService.cancelCommunicationScheduling(id);

            if(cancelled) {
                return new ResponseEntity<>("Communication schedule successfully canceled !", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No communication schedule found !", HttpStatus.OK);
            }


        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
