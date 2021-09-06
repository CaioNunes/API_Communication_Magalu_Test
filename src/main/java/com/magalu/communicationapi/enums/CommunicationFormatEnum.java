package com.magalu.communicationapi.enums;

import lombok.Getter;

@Getter
public enum CommunicationFormatEnum {

    EMAIL(1, "email"),
    SMS(2, "sms"),
    PUSH(3, "push"),
    WHATSAPP(4, "whatsapp");

    private Integer id;
    private String description;

    CommunicationFormatEnum(Integer id, String description){
        this.id = id;
        this.description = description;
    }

    public static CommunicationFormatEnum findCommunicationFormatByDescription(String description){
        for(CommunicationFormatEnum communicationFormatEnum : CommunicationFormatEnum.values()){
            if(communicationFormatEnum.getDescription().equals(description)){
                return communicationFormatEnum;
            }
        }

        return null;
    }

}
