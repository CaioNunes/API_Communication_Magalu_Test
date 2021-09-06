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
    public ResponseEntity<List<CommunicationSchedulingDTO>> getCommunicationSchedules(){
        try{
            List<CommunicationSchedulingDTO> communicationSchedules = communicationSchedulingService.getCommunicationSchedules();

            return new ResponseEntity<>(communicationSchedules, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
