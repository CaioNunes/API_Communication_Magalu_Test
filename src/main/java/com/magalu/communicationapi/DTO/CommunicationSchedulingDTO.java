package com.magalu.communicationapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommunicationSchedulingDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @DateTimeFormat
    @NotNull(message = "{date.time.submission.not.null}")
    @JsonProperty("dateTimeSubmission")
    private LocalDateTime dateTimeSubmission;

    @NotBlank(message = "{receiver.not.blank}")
    @JsonProperty("receiver")
    private String receiver;

    @NotBlank(message = "{message.not.blank}")
    @JsonProperty("message")
    private String message;

    @NotBlank(message = "{communication.format.not.blank}")
    @Pattern(regexp = "email|sms|push|whatsapp", message = "{communication.format.incorrect.pattern}")
    @JsonProperty("communicationFormat")
    private String communicationFormat;

    @JsonProperty("scheduleStatus")
    private String scheduleStatus;

}
