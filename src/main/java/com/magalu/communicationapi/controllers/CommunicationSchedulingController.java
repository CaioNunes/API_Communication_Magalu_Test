package com.magalu.communicationapi.controllers;

import com.magalu.communicationapi.DTO.CommunicationSchedulingDTO;
import com.magalu.communicationapi.services.CommunicationSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
