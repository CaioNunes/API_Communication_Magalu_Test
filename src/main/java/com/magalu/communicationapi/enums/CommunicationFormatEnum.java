package com.magalu.communicationapi.enums;

import lombok.Getter;

@Getter
public enum CommunicationFormatEnum {

    EMAIL(1, "E-Mail"),
    SMS(2, "SMS"),
    PUSH(3, "Push"),
    WHATSAPP(4, "WhatsApp");

    private Integer id;
    private String description;

    CommunicationFormatEnum(Integer id, String description){
        this.id = id;
        this.description = description;
    }

}
