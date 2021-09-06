package com.magalu.communicationapi.enums;

import lombok.Getter;

@Getter
public enum ScheduleStatusEnum {

    SCHEDULED(1, "Scheduled"),
    SENT(2, "Sent"),
    CANCELLED(3, "Cancelled");

    private Integer id;
    private String description;

    ScheduleStatusEnum(Integer id, String description){
        this.id = id;
        this.description = description;
    }

    public static ScheduleStatusEnum findScheduleStatusByDescription(String description){
        for(ScheduleStatusEnum scheduleStatus : ScheduleStatusEnum.values()){
            if(scheduleStatus.getDescription().equals(description)){
                return scheduleStatus;
            }
        }

        return null;
    }

}
